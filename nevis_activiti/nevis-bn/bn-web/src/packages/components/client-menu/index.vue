<template>
  <el-menu
    :default-active="activeIndex"
    class="mh-client-menu"
    mode="horizontal"
    @select="handleSelect">
    <el-menu-item index="1">
      <router-link to="/">处理中心</router-link>
    </el-menu-item>
    <el-submenu index="2"
                popper-class="mh-client-submenu">
      <template slot="title" class="aaaa">我的工作台</template>
      <el-menu-item index="2-1">
        <router-link to="/">选项1</router-link>
      </el-menu-item>
      <el-menu-item index="2-2">
        <router-link to="/">选项2</router-link>
      </el-menu-item>
      <el-menu-item index="2-3">
        <router-link to="/">选项3</router-link>
      </el-menu-item>
    </el-submenu>
    <el-menu-item index="3">
      <router-link to="/">消息中心</router-link>
    </el-menu-item>
    <el-menu-item index="4">
      <router-link to="/">订单管理</router-link>
    </el-menu-item>
  </el-menu>
</template>
<script>
  import './style.scss'

  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    computed: {},
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
