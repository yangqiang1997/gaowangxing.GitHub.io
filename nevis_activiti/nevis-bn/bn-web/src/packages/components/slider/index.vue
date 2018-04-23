<template>
  <div width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      class="el-menu-vertical-demo"
      :default-active="activeIndex"
      @select='handSelect'
      unique-opened
      :default-openeds="opends"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#409EFF"
      width="200px"
      :collapse="isCollapse"
    >
      <div v-for="(submenu, index) in menuList">
      <el-submenu  v-if="submenu.menuGroup" :index="submenu.to == '' ?undefined : submenu.to" :key="index">
        <template slot="title">
          <i :class="iconClass(submenu.icon)"></i>
          <span slot="title">{{submenu.subMenu}}</span>
        </template>
        <el-menu-item-group v-if="!menu.menuGroup" v-for="(menu, v) in submenu.menuGroup" :key="'-' + v">
          <router-link class="head-link" :to="menu.to">
            <el-menu-item :index="submenu.to+''+menu.to">
              <span slot="title">{{menu.label}}</span>
            </el-menu-item>
          </router-link>
        </el-menu-item-group>
        <el-submenu v-if="menu.menuGroup" v-for="(menu, v) in submenu.menuGroup" :key="'-' + v" :index="index+'-'+v">
              <span slot="title">{{menu.label}}</span>
              <el-menu-item-group v-for="(smenu, subv) in menu.menuGroup">
                <router-link class="head-link" :to="smenu.to">
                <el-menu-item :index="index+ '-' + v +'-' +subv" >
                  <span slot="title">{{smenu.label}}</span>
                </el-menu-item>
                </router-link>
              </el-menu-item-group>
        </el-submenu>
      </el-submenu>

          <el-menu-item v-if="!submenu.menuGroup"  :index="submenu.to" @click='handClick(submenu)'>
            <i class="el-icon-setting"></i>
            <span slot="title">{{submenu.submenu}}</span>
          </el-menu-item>
      </div>
      </el-menu>
    <div class="side_trgger" :style="{width: isCollapse?'64px':'200px'}" @click="collapse">
      <i  :class="menuToger" :title="menuTogerTitle"></i>
    </div>
  </div>
</template>
<script>
  import {commonApi} from '../../../tools/CommonApi'
  import menu from '../../../mock/menu.json'
  export default {
    name: 'slider',

    mounted() {
      this.getMenu();
    },
    updated() {
      let activeDom = document.getElementsByClassName("el-menu-item is-active");
      activeDom[0].style.backgroundColor = "rgb(38,52,69)";
    },
    data() {
      return {
        isCollapse: false,
        menuList: [],
        activeIndex: this.$route.path,
        opends:[this.activeIndex]
      }
    },
    watch: {
      $route(to,from) {
        this.handleSelect(to.path);
      }
    },
      methods: {
        handSelect: function (key, path ,item) {

        },
        handleSelect(index) {
          this.activeIndex = index;
        },
        iconClass(icon) {
          return 'iconfont' + ' ' + icon
        },
        collapse() {
          this.isCollapse = !this.isCollapse
        },
        handClick(submenu) {
          const router = submenu.to || '/'
          try {
            this.$router.push({path: router});
          } catch (e) {
            console.error(e);
          }
        },
        getMenu() {
          commonApi.getUserMenu().then(res => {
            this.menuList = res.data.data;
          }).catch(error => {

          })
        }
      },
      computed: {
        menuToger: function () {
          return !this.isCollapse ? 'el-icon-d-arrow-left' : 'el-icon-d-arrow-right'
        },
        menuTogerTitle: function () {
          return this.isCollapse ? '展开' : '收起'
        }
      },

  }

</script>

<style scoped lang="scss">
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

  .side_trgger {
    position: fixed;
    text-align: center;
    bottom: 0;
    cursor: pointer;
    height: 48px;
    line-height: 48px;
    color: #fff;
    background: #002140;
    z-index: 1;
    -webkit-transition: all .3s ease-in-out;
    transition: 0.3s width ease-in-out, 0.3s padding-left ease-in-out, 0.3s padding-right ease-in-out;
    width: 200px;
  }

  .el-menu-item {
    a {
      color: rgb(191, 203, 217);
    }
  }

  .el-menu {
    height: 100%;
  }

  .el-menu-item.is-active > a {
    color: #1890ff;
  }

  .el-menu-item-group a {
    text-decoration: none;
  }

  .el-menu-item a {
    text-decoration: none;
    display: block;
    width: 100%;
    height: 100%;
  }

  .head-link {
    &:active {
      text-decoration: none;
    }
  }

  .bg{
    background-color:rgb(38,52,69) !important;

  }
</style>
