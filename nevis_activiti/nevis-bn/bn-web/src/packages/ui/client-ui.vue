<template>
  <el-container>
    <el-header>
      <client-header>
      </client-header>
    </el-header>
    <el-main>
      <div class="mh-client-container">
        <router-view>
        </router-view>
      </div>
    </el-main>
    <el-footer>
      <client-footer>
      </client-footer>
    </el-footer>
  </el-container>
</template>

<script>
  import ClientHeader from './client-header'
  import ClientFooter from './client-footer'

  export default {
    components: {
      ClientHeader,
      ClientFooter
    }
    // todo
  }
</script>
<style scoped lang="scss">
  .el-header {
    height: 180px !important;
    padding: 0;
    width: 100%;
  }

  .el-footer {
    height: 133px !important;
    padding: 0;
    width: 100%;
  }

  .el-main {
    width: 100%;
    height: 100%;
    padding: 0;
    margin: 0;
  }

  .mh-client-container {
    max-width: 1000px;
    margin: 0 auto;
  }
</style>
