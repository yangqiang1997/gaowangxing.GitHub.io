<template>
  <div v-loading="loading">
    <el-form :model="resourceInfo" :rules="rules"  ref="resourceInfo" label-width="100px">

      <el-form-item label="上级菜单" prop="parentName">
        <el-input v-model="resourceInfo.parentName" type="string"  disabled>
        </el-input>
      </el-form-item>

      <el-form-item label="菜单名称" prop="resourceName">
        <el-input v-model="resourceInfo.resourceName" type="string" ></el-input>
      </el-form-item>

      <el-form-item label="路由地址" prop="resourcePath">
        <el-input v-model="resourceInfo.resourcePath" type="string" ></el-input>
      </el-form-item>


      <el-form-item label="图标类" prop="resourceIconClass"
                  >
        <el-input v-model="resourceInfo.resourceIconClass" placeholder="请输入图表类的地址" type="string" ></el-input>
      </el-form-item>

      <el-form-item label="显示顺序" prop="resourceShowOrder">
        <el-input v-model.number="resourceInfo.resourceShowOrder"  placeholder="请输入数字" ></el-input>
      </el-form-item>

      <el-form-item label="是否是菜单" prop="isMenu" >
        <el-radio v-model="resourceInfo.isMenu" label=1>是</el-radio>
        <el-radio v-model="resourceInfo.isMenu" label=0>否</el-radio>
      </el-form-item>

      <el-form-item label="权限标识" prop="authorizationToken">
        <el-input v-model="resourceInfo.authorizationToken" ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="save('resourceInfo')">保存</el-button>
        <el-button type="primary" @click="back">返回</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<style lang="scss" scoped></style>

<script >
  import {resourceApi} from './resource-api'
  import {Msg} from '@/tools/message'
  import {Tip} from '../../tools/systemConstants'
  export default {
    data() {
      return {
        resourceInfo: {
          parentResourceId: '',
          resourceName: '',
          resourcePath: '',
          resourceIconClass: '',
          resourceShowOrder: '',
          isMenu: '',
          authorizationToken: '',
          level0: '',
          parentName:''
        },
        selectResource: {
        },
        loading:false,
        rules:{
          resourceName:[{required:true,message:'请输入资源名称',trigger:'blur'},
            {max:30,message:'资源名称长度应小于30',trigger:'blur'}],
          //resourceShowOrder:[{ max:999 , message: '显示顺序必须为数字值',trigger:'change'}],
          isMenu:[{required:true,message:'必须选择',trigger:'blur'}],
          authorizationToken:[{required:true,message:'请输入权限标识',trigger:'blur'},
            {max:50,message:'权限标识长度应小于50',trigger:'blur'}]
        },
      }
    },

    mounted() {
      this.getParent();
    },

    methods: {
      back: function () {
        this.$router.push({path: '/resource'});
      },

      getParent: function () {
        this.loading = true;
      var id = this.$route.query.resourceId;
      var param = {resourceId: id};
       resourceApi.getResourceInfo(param).then((res) => {
         this.loading = false;
           this.selectResource = res.data.data;
         this.resourceInfo.parentResourceId = this.selectResource.resourceId;
         this.resourceInfo.parentName = this.selectResource.resourceName;
       }).catch((message)=>{
         this.loading = false;
         Msg.error(message);
       })

      },

      save: function (formName) {

        this.$refs[formName].validate((valid) =>{
          if(valid){
            this.loading = true;
            resourceApi.addResourceInfo(this.resourceInfo).then(res => {
              if (res.status == '200') {
                this.loading = false;
                Msg.success(Tip.ADD_SUCCESS);
                this.$router.push({path: '/resource'});
              }
              else if (res.status == '500') {
                this.loading = false;
                Msg.success(Tip.ADD_ERROR);
                this.$router.push({path: '/resource'});
              }
            }).catch((message)=>{
              this.loading = false;
              Msg.error(message);
            })
          }
          else{
            alert('请输入正确的信息');
          }

        })


      }
  }
  }
</script>
