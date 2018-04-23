<template>
  <div class="mh-login">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="0px"
             class="demo-ruleForm login-container">
      <h3 class="mh-login-title" >系统登录</h3>
      <el-form-item prop="username">
        <el-input
          type="text"
          v-model="ruleForm.username"
          ref="adminput"
          auto-complete="on"
          placeholder="请输入用户名">
          <i slot="prefix" class="icon iconfont icon-my"></i>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          auto-complete="off"
          placeholder="请输入密码"
        >
          <i slot="prefix" class="icon iconfont icon-jiesuo"></i>
        </el-input>
      </el-form-item>
      <el-form-item label="">
        <el-checkbox
          label="记住账号" name="type" v-model="ruleForm.remember"
          style="text-align: left">
        </el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button
          style="width: 100%;"
          type="primary"
          @click.native.prevent="submitForm"
          :id="login"
          :loading="loading">登陆
        </el-button>
      </el-form-item>
      <div class="mh-login-footer">重庆士继达新能源物流车运营平台 {{version}} | 重庆士继达新能源汽车有限公司™版权所有</div>
    </el-form>
  </div>
</template>

<script>
  import {version} from '../../../package'
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  import {ResponseCode} from '../../tools/ResponseCode'
  import {Auth} from '../../store/user/Auth'
  import axios from 'axios'

  export default {
    name: 'Login',
    components: {},
    /** state 默认信息 */
    data() {
      var validateRepetition = (rule, value, callback) => {
        if (value != this.oldValue) {
          //callback(new Error('请输入密码'));
          callback();
        } else {
          callback();
        }
      };
      return {
        loading: false,
        version,
        ruleForm: {
          username: '',
          password: '',
          remember: false
        },
        oldValue:'',
        rules: {
          username: [
            {required: true, message: '请输入登陆账户', trigger: 'change'},
            {min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入登陆密码', trigger: 'change'}
           // { validator:validateRepetition,trigger: 'change'}
          ],
        }
      }
    },
    created() {
      //监听enter事件
      window.addEventListener ? window.addEventListener('keyup', this.Enter) : null ;
      this.getUser();
    },
    /** 计算属性 */
    computed: {},
    /** 完成挂载 */
    mounted() {
      this.$refs['adminput'].focus();
    },
    destroyed() {
      window.removeEventListener('keyup', this.Enter)
    },
    /** 方法事件 */
    methods: {
      Enter() {
        document.onkeyup = (e) => {
          var currKey=0;
          let event= e ||window.event;
          currKey=e.keyCode||e.which||e.charCode;//支持IE、FF
          if (currKey == 13){
            if(event){
              e.returnValue = false;
            }else{
              e.preventDefault();
            }
            this.submitForm()
          }
        }
      },
      getUser(){
        this.ruleForm = Auth.getAccountPwd() || {};
       },
      submitForm() {
        this.$refs['ruleForm'].validate((valid) => {
          if (!valid)  return false;
          this.loading = true;
          this.$store.dispatch('accountLoginSubmit',this.ruleForm).then((res) => {
              this.loading = false;

              if(res.code == ResponseCode.SUCCESS) {
                Msg.success(Tip.LOGIN_SUCCESS);
                this.$router.push({path: '/'})
              }
             else{
                Msg.error(res.message);
              }
            }).catch((message) => {
              this.loading = false;
              Msg.error(message);
            })
        })
      },
    },
    /** 监听函数 */
    watch: {
      $route() {
        this.path = this.$route.path.split('/')[2]
      }
    }
  }
</script>

<style lang="scss" scoped>
  .mh-login {
    width: 100%;
    height: 100%;
    .el-form-item {
      .el-form-item__content {
        .icon {
          position: absolute;
          left: 8px;
          z-index: 1;
        }
      }
    }
    &-title {
      margin-bottom: 10px;
      text-align: center;
    }

    &-footer {
      font-size: 14px;
      color: #606266;
    }
  }

  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -60%);
    overflow: hidden;
    min-width: 270px;
    .title {
      text-align: center;
      color: #505458;
    }
  }
</style>
