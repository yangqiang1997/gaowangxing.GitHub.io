<template>
  <div style="width: 100%; float: right" >
    <div style="width: 23%;float: left;margin-left:2.5% ">
      <div align="center">
        <el-button
          size="big"
          type="primary"
          style="margin:0 auto; margin-bottom: 10%"
          @click="orgManage">管理组织机构</el-button>
      </div>
      <el-tree
        :data="treeData1"
        node-key="id"
        :render-content="renderContent"
        default-expand-all
        @node-click="getUsers"
        :props="defaultProps1"
        ref="tree">
      </el-tree>

    </div>
    <div style="width: 70%; float: right" >
      <h1>员工管理</h1>
      <div class="select">
        <el-form :inline="false" :model="formInline" class="elInput">
          <el-form-item>
            <table class="searchTable" align="left">
              <tr>
                <td><el-input v-model="formInline.account" placeholder="账号" style="width: 90%;"  clearable></el-input></td>
                <td><el-input v-model="formInline.userName" placeholder="用户名" style="width: 90%;" clearable></el-input></td>
                <td>
                  <el-select v-model="formInline.org" value-key="id" filterable placeholder="单位" style="width: 90%;" class="elSelect">
                    <el-option
                      v-for="item in orgOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                  <!--<el-cascader
                    v-model="orgOps"
                    :options="orgOptions"
                  ></el-cascader>-->
                </td>
              </tr>

              <tr>
                <td>
                  <el-select v-model="formInline.role" value-key="id" filterable placeholder="所属角色" style="width: 90%;" class="elSelect">
                    <el-option
                      v-for="item in roleOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </td>
                <td>
                  <el-select v-model="formInline.isLock" value-key="id" filterable placeholder="锁定状态" style="width: 90%;" class="elSelect">
                    <el-option
                      v-for="item in lockOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </td>
                <td><el-button type="primary" @click="onSubmit" class="searchSubmit">查询</el-button></td>
              </tr>
            </table>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <el-button type="primary" style="float: left;position:relative;top: 0px; width: 100px;margin-bottom: 1%" @click="addUser">新增</el-button>
      </div>

      <div class="table">
        <el-table :data="tableData" fit border>
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="account"
            label="账号"
            width="120">
          </el-table-column>
          <el-table-column
            prop="userName"
            label="用户名"
            width="80">
          </el-table-column>
          <el-table-column
            prop="orgName"
            label="单位"
            width="200">
          </el-table-column>
          <el-table-column
            prop="phoneNumber"
            label="联系电话"
            width="120">
          </el-table-column>
          <el-table-column
            prop="roleName"
            label="所属角色"
            width="80">
          </el-table-column>
          <el-table-column
            prop="isLock"
            label="状态"
            width="80">
          </el-table-column>
          <el-table-column
            prop="userRemark"
            label="备注">
          </el-table-column>
          <el-table-column
            label="操作"
            width="300"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              <el-button
                v-show="!scope.row.lockValue"
                size="mini"
                type="info"
                @click="handleStop(scope.$index, scope.row)">禁用</el-button>
              <el-button
                v-show="scope.row.lockValue"
                size="mini"
                type="float"
                @click="handleStart(scope.$index, scope.row)">启用</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="formInline.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="formInline.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="formInline.totalNum">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table{
    width: 60%;
    float: left;
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
</style>

<script>
  import {orgManagementApi} from './orgManagementApi'
  import {RoleManagementApi} from '../roleManagement/api'
  import {userManagement} from "../userManagement/userManagementApi";
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  export default {
    data() {
        return {
          lockOptions: [{value:null, label:'全部状态'},{value:true,label:'禁用'},{value:false,label:'启用'}],//是否锁定，默认为0和1
          multipleSelection: [],
          roleOptions: [],//所属角色选项
          orgOptions: [],
          options: [{
            value: 1,
            label: '男'
          }, {
            value: 0,
            label: '女'
          }],
          lockState:{
            value:'',
            show:true,
          },
          organizationId: null,
          pageSizesList:[15,20],
          /*搜索内容的属性*/
          formInline: {
            account: '',
            userName:'',
            isLock:'',//是否锁定的当前值
            role:'',//当前选择的所属角色值
            org:'',
            pageNum: 1,
            pageSize: 10,
            totalNum: null
          },
          formInline1: {
            organizationId: '',
            pageNum: 1,
            pageSize: 10,
            totalNum: null
          },
          ruleForm: {
            username: '',
            password: '',
            remember: false
          },
          /*表格数据*/
          tableData: [],
          imgUrl:"../../../assets/images/orgManagement1.png",
          treeData1: null,
          defaultProps1: {
            children: 'children',
            label: 'organizationName',
            organizationId: 'organizationId',
            expired: 'isExpired',
            organizationTypeId: 'organizationTypeId',
            parentOrganizationId: 'parentOrganizationId',
            lock: 'isLock'
          },
          dialogFormVisible: false,
          formLabelWidth: '100px',
          pickerOptions1: {
            shortcuts: [{
              text: '今天',
              onClick(picker) {
                picker.$emit('pick', new Date());
              }
            }, {
              text: '昨天',
              onClick(picker) {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24);
                picker.$emit('pick', date);
              }
            }, {
              text: '一周前',
              onClick(picker) {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                picker.$emit('pick', date);
              }
            }]
          }
        }
      },
    mounted() {
      this.getTreeData();
      this.getSelectData();
      this.getUsers();
    },
    methods: {
      getUser() {
        this.ruleForm = this.$store.getters.accountPwd
      },
      /**
       * 点击查询后执行函数
       */
      onSubmit:function () {
        userManagement.GetUserList(this.formInline).then(res => {
          this.formInline.pageSize = res.data.data.pageSize;
          this.formInline.pageNum = res.data.data.pageNum;
          this.formInline.totalNum = res.data.data.total;
          this.tableData = res.data.data.list;
          for(var i = 0; i < this.tableData.length; i++){
            if(res.data.data.list[i].isLock){
              this.tableData[i].isLock = "禁用";
              this.tableData[i].lockValue = true;
            } else {
              this.tableData[i].isLock = "启用";
              this.tableData[i].lockValue = false;
            }
          }
        }).catch((message)=>{
          Msg.error(message);
        })
      },
      getTableData:function () {
        userManagement.GetUserList(this.formInline).then(res =>{
          this.formInline.pageSize = res.data.data.pageSize;
          this.formInline.pageNum = res.data.data.pageNum;
          this.formInline.totalNum = res.data.data.total;
          this.tableData = res.data.data.list;
          for(var i = 0; i < this.tableData.length; i++){
            if(res.data.data.list[i].isLock){
              this.tableData[i].isLock = "禁用";
              this.tableData[i].lockValue = true;
            } else {
              this.tableData[i].isLock = "启用";
              this.tableData[i].lockValue = false;
            }
          }
        }).catch((message)=>{
          Msg.error(message);
        })
      },
      getSelectData:function () {
        this.getRoleSelectData();
        this.getOrgSelectData();
      },

      getRoleSelectData:function () {
        this.roleOptions.push();
        const  param = {withChildren:1};/*const定义的变量只能赋值一次*/
        userManagement.GetRoleSelectData(param).then(res =>{
          let option = res.data.data;
          this.roleOptions.push({value:null, label:'全部角色'});
          for(var i = 0; i < option.length;i++) {
            let param = {value:option[i].roleId, label:option[i].roleName};
            this.roleOptions.push(param);
          }
        }).catch((message) => {
          Msg.error(message);
        })
      },
      getOrgSelectData:function () {
        this.orgOptions.push();
        const  param = {withChildren:1};/*const定义的变量只能赋值一次*/
        userManagement.GetOrgSelectData(param).then(res =>{
          let option = res.data.data;
          this.orgOptions.push({value:null, label:'全部单位'});
          for(var i = 0; i < option.length;i++) {
            let param = {value:option[i].organizationId, label:option[i].organizationName};
            this.orgOptions.push(param);
          }
        }).catch((err) => {
          Msg.error(err);
        })
      },
      addUser:function () {
        this.$router.push({path: './addNewUser',query:{type:1,orgId:this.organizationId}});
      },
      handleEdit:function (index,row) {
        var param = {userId:row.userId};
        this.$router.push({path:'./addNewUser',query:{index:index,row:row,type:1}});
      },
      handleDelete:function(index,row) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          var param = {userId:row.userId};
          userManagement.DeleteUser(param).then((res)=>{
            if(res.data.data){
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }
            this.getTableData();
          });
        }).catch((message) => {
          Msg.error(message);
        });
      },
      handleStop(index,row){
        this.$confirm('此操作将禁用该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.changeLockState(row.userId,row.lockValue)
          this.$message({
            type: 'success',
            message: '已禁用用户'+row.userName
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
        this.getTableData();
      },
      handleStart(index,row){
        this.$confirm('此操作将启用该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.changeLockState(row.userId,row.lockValue)
          this.$message({
            type: 'success',
            message: '已启用用户'+row.userName
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
        this.getTableData();
      },
      changeLockState:function (userId,lockValue) {
        var param = {userId:userId,isLock:!lockValue};
        userManagement.ChangeUserLockState(param).then((res)=>{
          this.getTableData();
        })
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.formInline.pageSize = val;
        this.getTableData();
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.formInline.pageNum = val;
        this.getTableData();
      },
      orgManage : function () {
        this.$router.push({path: 'orgManage'})
      },
      /*获取树的数据*/
      getTreeData: function () {
        orgManagementApi.GetSourceListApi().then(res => {
          console.log(res);
          if(res.data.data != null){
            this.treeData1 = res.data.data;
            console.log(this.treeData1);
          }
        }).catch((message)=>{
          Msg(message);
        });
      },
      getUsers: function (data){
        if(data){
          this.organizationId = data.organizationId;
          this.formInline.org = data.organizationId;
        }
        userManagement.GetUserList(this.formInline).then(res => {
          this.formInline.pageSize = res.data.data.pageSize;
          this.formInline.pageNum = res.data.data.pageNum;
          this.formInline.totalNum = res.data.data.total;
          this.tableData = res.data.data.list;
          for(var i = 0; i < this.tableData.length; i++){
            if(res.data.data.list[i].isLock){
              this.tableData[i].isLock = "禁用";
              this.tableData[i].lockValue = true;
            } else {
              this.tableData[i].isLock = "启用";
              this.tableData[i].lockValue = false;
            }
          }
        }).catch((message)=>{
          Msg(message);
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
            if (res.status == '200') {
              Msg.success(Tip.delSucess);
              this.getTableData();
            } else {
              Msg.error(Tip.delError);
            }
          }).catch((message)=>{Msg.error(message)});
        }).catch(() => {
          Msg.info(Tip.delCancel);
        });
      },handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.formInline.pageSize = val;
        this.getTableData();
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.formInline.pageNum = val;
        this.getTableData();
      }
    }
  }
</script>
