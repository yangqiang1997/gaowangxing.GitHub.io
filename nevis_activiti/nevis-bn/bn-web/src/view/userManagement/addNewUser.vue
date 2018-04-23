<template>
  <div>
    <div class="detailTitle">
      <p class="titleWords">基本信息</p>
    </div>
    <el-form :model="user" status-icon :inline="true"  ref="user" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item style="width: 40%"
                    label="账号"
                    prop="account">
        <el-input v-bind:disabled="!isShow" type="title" v-model="user.account" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="用户名"
        prop="userName">
        <el-input type="source" v-model="user.userName" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item v-show="isShow"
                    style="width: 40%;"
                    label="密码"
                    prop="password">
        <el-input type="password" v-model="user.password" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item v-show="isShow"
                    style="width: 40%;"
                    label="重复密码"
                    prop="repassword">
        <el-input type="password" v-model="user.repassword" auto-complete="off"></el-input>
      </el-form-item>

      <div class="detailTitle">
        <p class="titleWords">详细信息</p>
      </div>

      <el-form-item
        style="width: 40%;"
        label="联系电话"
        prop="phoneNumber">
        <el-input type="source" v-model="user.phoneNumber" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="所属角色"
        prop="role">
        <el-select v-model="user.role" value-key="id" filterable placeholder="角色" style="width: 207px;" class="elSelect">
          <el-option
            v-for="item in roleOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        prop="birth"
        label="出生日期">
        <el-date-picker
          v-model="user.birth"
          type="date"
          style="width: 87%;"
          placeholder="选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="性别"
        prop="sex">
        <el-radio-group v-model="user.sex">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="单位名称"
        prop="org">
        <el-select v-model="user.org" value-key="id" filterable placeholder="单位" style="width: 207px;" class="elSelect">
          <el-option
            v-for="item in orgOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="邮箱"
        prop="eMail">
        <el-input type="source" v-model="user.eMail" auto-complete="off" clearable></el-input>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="QQ"
        prop="QQNumber">
        <el-input type="source" v-model="user.QQNumber" auto-complete="off" clearable></el-input>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="微信号"
        prop="weChatNumber">
        <el-input type="source" v-model="user.weChatNumber" auto-complete="off" clearable></el-input>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="是否锁定"
        prop="isLock">
        <el-select style="width: 207px;" v-model="user.isLock" placeholder="请选择初始状态">
          <el-option
            v-for="item in LockOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        style="width: 40%;"
        label="备注"
        prop="remark">
        <el-input type="textarea" style="width: 207px;" v-model="user.remark"></el-input>
      </el-form-item>
      <el-form-item style="width: 40%;"></el-form-item>
    </el-form>
    <div style="margin-left: 46%;">
      <el-button type="primary" @click="submit" :disabled="isDisable" :loading="isLoading">保存</el-button>
      <el-button type="primary" @click="back()" >返回</el-button>
    </div>
  </div>
</template>
<script>
  import CompEditor from 'pkgs/components/editor'
  import CompUpload from 'pkgs/components/upload'
  import {userManagement} from './userManagementApi'
  import ElHeader from "element-ui/packages/header/src/main";
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  export default {
    components: {
      ElHeader,
      CompEditor,
      CompUpload
    },
    mounted() {
      this.getRoleSelectData();
      this.getOrgSelectData();
      this.getUserInfo();
    },
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.user.repassword !== '') {
            this.$refs.user.validateField('repassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.user.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      var validatePhonenumber = (rule, value, callback) => {
        var reg=11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/;
        if (value === '') {
          callback(new Error('请输入手机号码'));
        } else if (!reg.test(value)) {
          callback(new Error('请输入正确的手机号!'));
        } else {
          callback();
        }
      };
      var validateEmail = (rule, value, callback) => {
        var regEmail= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z0-9]{2,5}$/;

        if(value.trim().length != 0){
        if (!regEmail.test(value)) {
          callback(new Error('邮箱格式不正确!'));
        } else {
          callback();
        }}
        else{
          return;
        }

      };
      var validateAccount = (rule, value, callback) => {
        var reg=11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/;
        if (value === '') {
          callback(new Error('请输入账号'));
        } else if(!reg.test(value)){
          callback(new Error('账号为联系电话!'));
        } else if(value!==''){
          this.accountForm.account = value;
          userManagement.CheckAccount(this.accountForm).then(res => {
            if (res.data.data) {
              callback(new Error('账号已存在'));
            } else {
              callback();
            }
          }).catch((message)=>{
            Msg(message);
          })
        }
      };
      return {
        roleOptions: [],
        orgOptions:[],
        LockOptions:[{value:true,label:'禁用'},{value:false,label:'启用'}],
        user: {
          account:'',
          userName: '',
          password:'',
          repassword:'',
          birth:'',
          role:'',
          org:'',
          sex:'',
          phoneNumber:'',
          eMail:'',
          QQNumber:'',
          weChatNumber:'',
          isExpired:'',
          isLock:'',
          remark:''
        },
        lockValue:'',
        accountForm: {
          account: '',
        },
        orgId:'',
        orgLength:'',
        orgs:[],
        isShow:true,
        isDisable:false,
        isLoading:false,
        stick:true,
        type:'',
        rules: {
          account:[
            { validator: validateAccount, required:true, trigger: 'blur'}
          ],
          userName: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, required: true, trigger: 'blur' }
          ],
          repassword: [
            { validator: validatePass2, required: true, trigger: 'blur' }
          ],
          phoneNumber: [
            { validator: validatePhonenumber, required: true, trigger: 'blur' }
          ],
          isLock: [
            { required: true, message: '请选择初始状态', trigger: 'change'}
          ],
          eMail: [
            { validator: validateEmail, required: false, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      /**当编辑跳转时获取用户信息*/
      getUserInfo:function () {
        if(typeof (this.$route.query.orgId)!="undefined"){
          this.user.org=this.$route.query.orgId;
        }
        if(typeof(this.$route.query.type)!="undefined"){
          this.type=this.$route.query.type;
        }
        var param = {userId:this.$route.query.row.userId}
        if(typeof(this.$route.query.row)!="undefined"){
          userManagement.GetUser(param).then(res =>{
            var user = {
              userId:this.$route.query.row.user,
              account:res.data.data.account,
              userName:res.data.data.userName,
              password:res.data.data.password,
              birth:res.data.data.birthdate,
              sex:res.data.data.userSex,
              phoneNumber:res.data.data.phoneNumber,
              role:res.data.data.roleId,
              org:res.data.data.orgId,
              eMail:res.data.data.userEmail,
              QQNumber:res.data.data.qqNumber,
              weChatNumber:res.data.data.wechartNumber,
              isLock:res.data.data.isLock,
              remark:res.data.data.userRemark
            }
            this.user=user;
          }).catch((message) =>{
            Msg(message);
          })
          this.isShow = false
        }
      },
      getRoleSelectData:function () {
        this.roleOptions.push();
        const  param = {withChildren:1};/*const定义的变量只能赋值一次*/
        userManagement.GetRoleSelectData(param).then(res =>{
          let option = res.data.data;
          for(var i = 0; i < option.length;i++) {
            let param = {value:option[i].roleId, label:option[i].roleName};
            this.roleOptions.push(param);
          }
        }).catch((message) => {
          Msg(message);
        })
      },
      getOrgSelectData:function () {
        this.orgOptions.push();
        const  param = {withChildren:1};/*const定义的变量只能赋值一次*/
        userManagement.GetOrgSelectData(param).then(res =>{
          let option = res.data.data;
          for(var i = 0; i < option.length;i++) {
            let param = {value:option[i].organizationId, label:option[i].organizationName};
            this.orgOptions.push(param);
          }
        }).catch((message) => {
          Msg(message);
        })
      },
      back:function(){
          if (this.type==0){
            this.$router.push({path: './userManagement'});
          } else if (this.type==1){
            this.$router.push({path: './orgManagment'});
          } else {
            this.$router.push({path: './userManagement'});
          }
      },
      /*新增用户保存*/
      submit:function(){
        var newUser;
        if (typeof(this.$route.query.row)=="undefined"){
          newUser = {
            userId:'',
            isLock:''+this.user.isLock,
            account:''+this.user.account,
            userName:''+this.user.userName,
            password:this.user.password,
            userSex:this.user.sex,
            phoneNumber:''+this.user.phoneNumber,
            roleId:this.user.role,
            orgId:this.user.org,
            userEmail:''+this.user.eMail,
            qqNumber:''+this.user.QQNumber,
            wechartNumber:''+this.user.weChatNumber,
            userRemark:''+this.user.remark
          }
          if(this.user.birth!= "" &&  this.user.birth!=undefined && this.user.birth!=null) {
            newUser.birthdate = '' + this.formatDateTime(this.user.birth);
          }
          if(newUser.userName === '' || newUser.account === '' || newUser.password === ''
            || newUser.phoneNumber === '' || newUser.isLock === '') {
            this.$alert('请填写完必要信息！', '填写信息', {
              confirmButtonText: '确定',
            });
            return;
          }
          if(this.user.repassword != newUser.password){
            this.$alert('请再次输入密码！', '输入密码', {
              confirmButtonText: '确定',
            });
            return;
          }
          userManagement.AddNewUser(newUser).then(res =>{
            if(res.data.message != null){
              this.$alert(res.data.message, {
                confirmButtonText: '确定',
              });
              return;
            }
            if(res.status == '200'){
              Msg.success(Tip.ADD_SUCCESS);
              this.back();
            }else{
              Msg.error(Tip.ADD_ERROR);
              this.back();
            }
          }).catch((message)=>{
            Msg(message);
          });
        } else {
          newUser = {
            userId: this.$route.query.row.userId,
            isLock:''+this.user.isLock,
            account:''+this.user.account,
            userName:''+this.user.userName,
            birthdate:''+this.formatDateTime(this.user.birth),
            userSex:this.user.sex,
            phoneNumber:''+this.user.phoneNumber,
            roleId:this.user.role,
            orgId:this.user.org,
            userEmail:''+this.user.eMail,
            qqNumber:''+this.user.QQNumber,
            wechartNumber:''+this.user.weChatNumber,
            userRemark:''+this.user.remark
          }
          if(newUser.userName === '' || newUser.account === '' || newUser.password === ''
            || newUser.phoneNumber === '' || newUser.isLock === '') {
            this.$alert('请填写完必要信息！', '填写信息', {
              confirmButtonText: '确定',
            });
            return;
          }
          userManagement.UpdataUser(newUser).then(res =>{
            if(res.data.message != null){
              this.$alert(res.data.message, {
                confirmButtonText: '确定',
              });
              return;
            }
            if(res.status == '200'){
              Msg.success(Tip.EDIT_SUCCESS);
              this.$router.push({path: './userManagement'});
            }else{
              Msg.error(Tip.EDIT_ERROR);
            }
          }).catch((message)=>{
            Msg(message);
          })
        }
      },
      formatDateTime(inputTime) {
        return new Date(inputTime);
      }
    }
  }
</script>
<style lang="scss" scoped>
  .card-body {
    margin-bottom: 10px;
    .ql-toolbar, .ql-container {
      border-color: red !important;
    }
  }
  .detailTitle{
    padding-top: 1px;
    height: 50px;
    text-align: left;
    width: 100%;
    margin-top: 10px;
    border-left: 3px solid lightskyblue;
    .titleWords{
      margin:10px;
      color: #555555;
      font-size: 20px;
    }
  }
  .demo-ruleForm{
    text-align: center;
  }
</style>
