<template>
  <div>
    <el-form ref="form" :model="formData" label-width="80px">
      <span class="promtSpan">角色名</span>
      <el-input v-model="formData.roleName" style="width: 200px" @keyup.enter.native="search"></el-input>
      <!--<span class="promtSpan">状态</span>
      <el-select v-model="formData.state" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>-->
      <el-button type="primary" class="searchButton" @click="search">搜索</el-button>
    </el-form>
    <br/>
    <el-button type="primary" class="addButton" @click="$router.push('/addRole')" v-auth="{id:'ROLE_ADD'}">新增</el-button>
    <el-button type="primary" class="addButton" @click="deleteSelectRole" :loading="loading" v-auth="{id:'ROLE_DELETE'}">删除</el-button>

    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
      v-loading="loading">
      <el-table-column
        type="selection"
        width="55"
        :selectable="selectable">
      </el-table-column>

      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="创建用户 :">
              <span>{{ props.row.createPerson }}</span>
            </el-form-item>
            <el-form-item label="创建时间 :">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column
        prop="roleName"
        label="角色名"
        min-width="18%">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        min-width="18%">
      </el-table-column>
      <el-table-column
        prop="canEdit"
        label="允许修改"
        min-width="18%">
      </el-table-column>
      <el-table-column
        prop="canDelete"
        label="允许删除"
        min-width="18%">
      </el-table-column>
      <el-table-column
        label="操作"
        min-width="25%">
        <template slot-scope="scope">
          <el-button @click="distributionJurisdiction(scope.row)" type="text" size="medium">分配权限</el-button>
          <el-button @click="distributionDataScope(scope.row)" type="text" size="medium">数据范围</el-button>
          <el-button v-if="scope.row.actualAllowEdit" @click="modify(scope.row.roleId)" type="text" size="medium">修改</el-button>
          <el-button v-if="!scope.row.actualAllowEdit" @click="cannotModify" type="text" size="medium" class="gray">修改</el-button>
          <el-button v-if="scope.row.allowDelete" @click="deleteTheRole(scope.row.roleId)" type="text" size="medium">删除</el-button>
          <el-button v-if="!scope.row.allowDelete" @click="cannotDelete" type="text" size="medium" class="gray">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <br/>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="formData.pageNum"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="formData.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="formData.totalNum">
    </el-pagination>
  </div>
</template>

<script>

  import {RoleManagementApi} from './api'
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  import {ResponseCode} from '../../tools/ResponseCode'

  export default {
    name: 'roleManagement',
    data() {
      return {
        loading: false,
        multipleSelection: [],
        formData: {
          roleName: null,
          pageNum: 1,
          pageSize: 10,
          totalNum: null
        },
        options: [{
          value: null,
          label: '请选择'
        }, {
          value: '选项1',
          label: '启用'
        }, {
          value: '选项2',
          label: '禁用'
        }],
        tableData: null
      }
    },
    mounted() {
      this.getTableData();
    },
    methods: {
      selectable: function (row) {
        return row.allowDelete;
      },
      search: function () {
        this.getTableData();
      },
      cannotDelete: function () {
        this.$message({
          type: 'success',
          message: '此角色不允许删除!'
        });
      },
      cannotModify: function () {
        this.$message({
          type: 'success',
          message: '此角色不允许修改!'
        });
      },
      modify: function (id) {
        this.$router.push({path: '/modifyRole', query: {id: id}});
      },
      distributionJurisdiction: function (data) {
        this.$router.push({path: '/distributionJurisdiction', query: {data: data}});
      },
      distributionDataScope: function (data) {
        this.$router.push({path: '/distributionDataScope', query: {data: data}});
      },
      getTableData: function () {
        this.loading = true;
        RoleManagementApi.GetListApi(this.formData).then(res => {
          if(res.data.code == ResponseCode.SUCCESS){
            this.formData.totalNum = res.data.data.total;
            this.tableData = res.data.data.list;
            for(var i = 0 ; i < this.tableData.length ; i++){
              if (this.tableData[i].allowEdit == true){
                this.tableData[i].canEdit = '允许';
              }else {
                this.tableData[i].canEdit = '不允许';
              }
              if (this.tableData[i].allowDelete == true){
                this.tableData[i].canDelete = '允许';
              }else {
                this.tableData[i].canDelete = '不允许';
              }
            }
            this.loading = false;
          }else {
            Msg.error(Tip.serverError);
          }
        }).catch(err => {
          Msg.error(Tip.SERVER_ERROR);
          this.loading = false;
        });
      },
      deleteSelectRole: function (id) {
        this.$confirm('此操作将永久删除选定角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          loading:true
        }).then(() => {
          if(this.multipleSelection != null && this.multipleSelection.length != 0){
            this.loading = true;
            var ids = '';
            for(var i = 0 ; i < this.multipleSelection.length ; i++){
              if(i < this.multipleSelection.length-1){
                ids += (this.multipleSelection[i].roleId + ',');
              }else {
                ids += this.multipleSelection[i].roleId;
              }
            }
            RoleManagementApi.DeleteSelectRole(ids).then(res => {
              if (res.data.code == ResponseCode.SUCCESS) {
                Msg.success(Tip.DEL_SUCCESS);
                this.getTableData();
              } else {
                Msg.error(Tip.DEL_EROOR);
              }
              this.loading = false;
            }).catch(err => {
              Msg.error(Tip.DEL_EROOR);
              this.loading = false;
            });
          }else {
            Msg.info("请至少选择一个选项");
          }
        }).catch(() => {
          Msg.info(Tip.CANCEL_DEL);
        });
      },
      deleteTheRole: function (id) {
        this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          loading:true
        }).then(() => {
          RoleManagementApi.DeleteARole(id).then(res => {
            if (res.data.code == ResponseCode.SUCCESS) {
              Msg.success(Tip.DEL_SUCCESS);
              this.getTableData();
            } else {
              Msg.error(Tip.DEL_EROOR);
            }
          }).catch(err => {
            Msg.error(Tip.DEL_EROOR);
          });
        });
      },
      handleSizeChange(val) {
        this.formData.pageSize = val;
        this.getTableData();
      },
      handleCurrentChange(val) {
        this.formData.pageNum = val;
        this.getTableData();
      },handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }

</script>

<style lang="scss" scoped>
  .promtSpan {
    margin-left: 30px;
    margin-right: 10px;
  }

  .searchButton {
    width: 100px;
    margin-left: 50px
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .addButton {
    width: 100px;
    margin-left: 30px;
    margin-bottom: 30px
  }
  .gray{
    color: #6E6E6E;
  }
</style>
