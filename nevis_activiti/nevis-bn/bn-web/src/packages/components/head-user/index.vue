<template>
  <el-dropdown>
    <span class="pointer"><i class="icon iconfont icon-my"></i>{{this.userInfo}}</span>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item><i class="icon iconfont icon-yanzhengma"></i>修改密码</el-dropdown-item>
      <el-dropdown-item @click.native="handleLoginOut"><i class="icon iconfont icon-tuichu" ></i>退出登陆</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>

  import {Auth} from '../../../../src/store/user/auth'
  import {Msg} from '../../../tools/message'
  import {Tip} from '../../../tools/systemConstants'
  export default {
    name: 'comp-bread',
    data() {
      return {
        userInfo: Auth.getUserInfo()
      }
    },
    props: {},
    methods: {
      handleLoginOut() {
        //加参数
        this.$store.dispatch('accountLogoutSubmit').then((res) => {
          this.$router.push({path: '/login'})
        }).catch(() => {
          Msg.error(Tip.SERVER_ERROR);
        })
      }
    },
  }
</script>
