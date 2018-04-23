<template>
  <div>
    <div class="select">
      <el-form :inline="false" :model="formInline" class="elInput">
        <el-form-item>
          <table class="searchTable" align="right">
            <tr>
              <td><el-input v-model="formInline.account" placeholder="账号" style="width: 60%;"  clearable></el-input></td>
              <td><el-input v-model="formInline.userName" placeholder="用户名" style="width: 60%;" clearable></el-input></td>
              <td>
                <el-select v-model="formInline.org" value-key="id" filterable placeholder="单位" style="width: 60%;" class="elSelect">
                  <el-option
                    v-for="item in orgOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                <!--<el-cascader
                  placeholder="选择单位"
                  v-model="orgOps"
                  :options="orgOptions"
                ></el-cascader>-->
              </td>
            </tr>

            <tr>
              <td>
                <el-select v-model="formInline.role" value-key="id" filterable placeholder="所属角色" style="width: 60%;" class="elSelect">
                  <el-option
                    v-for="item in roleOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </td>
              <td>
                <el-select v-model="formInline.isLock" value-key="id" filterable placeholder="锁定状态" style="width: 60%;" class="elSelect">
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
      <el-button type="primary" style="float: left;position:relative;top: 70px; width: 100px;" @click="addUser">新增</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" border>
        <!--<el-table-column
          type="selection"
          width="55">
        </el-table-column>-->
        <el-table-column
          prop="account"
          label="账号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="userName"
          label="用户名"
          width="120">
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
          width="60">
        </el-table-column>
        <el-table-column
          prop="userRemark"
          label="备注">
        </el-table-column>
        <el-table-column
          label="操作" width="220">
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
</template>

<script>
  import {userManagement} from "./userManagementApi";
  import ElSlPanel from "element-ui/packages/color-picker/src/components/sv-panel";
  export default {
    components: {ElSlPanel},
    mounted() {
      /*获取所属角色下拉框内容*/
      this.getSelectData();
      /*获取用户列表的数据*/
      this.getTableData();
    },
    created() {
      // window.addEventListener('keydown', this.Enter);
      this.getUser();
    },
    data() {
      return {
        lockOptions: [{value:null, label:'全部状态'},{value:true,label:'禁用'},{value:false,label:'启用'}],//是否锁定，默认为0和1
        roleOptions: [],//所属角色选项
        orgOptions: [],
        lockState:{
          value:'',
          show:true,
        },
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
        ruleForm: {
          username: '',
          password: '',
          remember: false
        },
        /*表格数据*/
        tableData: []
      }
    },
    methods: {
      getUser() {
        this.ruleForm = this.$store.getters.accountPwd
      },
      /**
       * 点击查询后执行函数
       */
      onSubmit:function () {
        //this.formInline.org = this.orgOps[this.orgOps.length - 1];
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
        //this.formInline.org = this.orgOps[this.orgOps.length - 1];
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
          Msg(message);
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
        }).catch((message) => {
          Msg.error(message);
        })
      },
      addUser:function () {
        this.$router.push({path: './addNewUser',query:{type:0}});
      },
      handleEdit:function (index,row) {
        var param = {userId:row.userId};
        this.$router.push({path:'./addNewUser',query:{index:index,row:row,type:0}});
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
          }).catch((message)=>{Msg(message)});
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
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
        }).catch((message)=>{
          Msg(message);
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
      }
    }
  }
</script>

<style lang="scss" scoped>
  .select {
    width: 100%;
    height: 60px;
    margin-top: -10px;
  }
  .elSelect {
    width: 65%;
    height: 25px !important;
  }
  .elInput {
  }
  .searchSubmit {
    width: 100px;
  }
  .table {
    width: 100%;
    height: 600px;
    margin-top: 120px;
  }
  .searchTable{
    width: 100%;
  }
  .searchTable tr{
    height: 50px;
  }
  .prompt{
    text-align: right;
  }
</style>
