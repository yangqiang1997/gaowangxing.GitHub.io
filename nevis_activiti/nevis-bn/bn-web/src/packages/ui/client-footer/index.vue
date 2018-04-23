<template>
  <div class="mh-client-footer">
    <div class="mh-client-footer-container">
      <div>
        <a href="http://121.43.68.40/exposure/jiucuo.html?site_code=5000000005&url=http%3A%2F%2Fwww.cqedu.cn%2F">
          <img src="../../../assets/images/u200.png" alt="">
        </a>
      </div>
      <div>
        <a href="http://biaozhi.conac.cn/">
          <img src="../../../assets/images/u199.png" alt="">
        </a>
      </div>
      <div class="mh-client-footer-text">
        <div>版权所有 重庆市学生资助金（教育经费监管）中心</div>
        <div>渝ICP备11001970号-1 渝公网安备 50010502000245号</div>
        <div>地址：重庆市江北区北滨一路369号 邮政编码：400020</div>
      </div>
    </div>
  </div>
</template>
<script>
</script>
<style scoped lang="scss">
  @import '../../../assets/styles/var';

  .mh-client-footer {
    height: 100%;
    width: 100%;
    background-color: #f2f2f2;
    &-container {
      display: flex;
      width: 1000px;
      margin: 0 auto;
      align-items: center;
      height: 100%;
      justify-content: center;
    }
    &-text {
      margin-left: 15px;
      > div {
        font-size: 14px;
        color: $font_text_color;
        margin: 5px 0;
      }
    }
  }
</style>
