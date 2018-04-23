<template>
  <div class="mh-client-header">
    <div class="mh-client-header-title">
      <div class="mh-client-header-img">
        <img :src="BgImage" alt="头部背景">
      </div>
      <div class="mh-client-header-text">
        <div class="mh-client-header-text-cont">
          <h1>重庆市学生资助金（教育经费监管）中心</h1>
          <span>CHONG QING SHI JIAO YUE WEI YUAN HUI</span>
        </div>
      </div>
    </div>
    <div class="mh-client-header-menu">
      <div class="mh-client-header-menu-cont">
        <client-menu>
        </client-menu>
      </div>
    </div>
  </div>
</template>

<script>
  import ClientMenu from '../../components/client-menu'
  import BgImage from '@/assets/images/u10.png'

  export default {
    name: 'client-header',
    components: {
      ClientMenu
    },
    data() {
      return {
        BgImage: BgImage
      }
    }
  }
</script>
<style scoped lang="scss">
  .mh-client-header {
    position: relative;
    height: 100%;
    &-img {
      height: 100%;
      img {
        width: 100%;
        height: 100%;
      }
    }
    &-title {
      height: 140px;
      width: 100%;
      position: relative;
    }
    &-text {
      position: absolute;
      top: 50px;
      left: 0;
      width: 100%;
      &-cont {
        width: 1000px;
        margin: 0 auto;
        h1, span {
          color: #ffffff;
          font-weight: bold;
        }
        h1 {
          font-size: 32px;
        }
      }
    }
    &-menu {
      height: 40px;
      background-color: #0299cc;
      &-cont {
        width: 1000px;
        margin: 0 auto;
        height: 100%;
      }
    }
  }
</style>
