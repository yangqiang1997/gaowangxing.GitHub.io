<template>
  <div v-loading="loading">
 <el-form :model="resourceInfo"   :rules="rules" ref="resourceInfo" label-width="100px">

  <el-form-item label="上级菜单" prop="parentName">
    <el-input v-model="resourceInfo.parentName"  type="string" disabled>
    <el-button slot="append"  @click="showResource" icon="el-icon-search"></el-button></el-input>
  </el-form-item>

  <el-form-item label="菜单名称" prop="resourceName">
    <el-input v-model="resourceInfo.resourceName" type="string" ></el-input>
  </el-form-item>

  <el-form-item label="路由地址" prop="resourcePath">
    <el-input v-model="resourceInfo.resourcePath" type="string" ></el-input>
  </el-form-item>


   <el-form-item label="图标类" prop="resourceIconClass">
     <el-input v-model="resourceInfo.resourceIconClass" placeholder="请输入图表类的地址" type="string" ></el-input>
   </el-form-item>

   <el-form-item label="显示顺序" prop="resourceShowOrder">
     <el-input v-model.number="resourceInfo.resourceShowOrder" type="number"  placeholder="请输入数字"></el-input>
   </el-form-item>

   <el-form-item label="是否是菜单" prop="isMenu">
   <el-radio v-model="resourceInfo.isMenu" label=1>是</el-radio>
     <el-radio v-model="resourceInfo.isMenu" label=0>否</el-radio>
   </el-form-item>

   <el-form-item label="权限标识" prop="authorizationToken">
     <el-input v-model="resourceInfo.authorizationToken" ></el-input>
   </el-form-item>

   <el-form-item>
     <el-button type="primary"  @click="save('resourceInfo')">保存</el-button>
     <el-button type="primary" @click="back">返回</el-button>
   </el-form-item>
  </el-form>


    <div v-show="show" class="openWin" v-loading="littleLoading">

      <div  class="littleWin">

        <div class="search">
          <span>菜单名称: </span>
          <el-input type="string"  placeholder='请输入菜单名称' v-model="resourceName" style="width:50%;height:80%;" @keyup.enter.native="search"></el-input>
          <el-button type="primary"  @click="search">搜索</el-button>
        </div>

        <div class="content">
        <el-tree :data="totalTreeResource" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
      </div>

      <div class="buttonlocation">
      <el-button type="primary" @click="ensure">保存</el-button>
      <el-button   @click="close">返回</el-button>
      </div>

      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.openWin{
  width:100%;
  height:100%;
  background-color:rgba(186,196,0,0.5);
  text-align:left;
  position:absolute;
  top:0;
  left:0;
  }
 .littleWin{
   background-color:white;
   width:35%;
   min-height: 500px;
   margin-top:5%;
   margin-left:30%;
}
 .content{
   margin-top:20px;
   margin-left:20px;
   min-height:360px;
 }
 .buttonlocation{
   margin-top:10px;
   margin-left: 10px;
 }
.search{
  width:100%;
  height: 10%;
  margin-buttom:20px;
padding-top: 20px;
}
</style>

<script >
import {resourceApi} from './resource-api'
import {Msg} from '@/tools/message'
import {Tip} from '../../tools/systemConstants'

 export default {

   components: {

   },

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
         level0:'',
         parentName:''
       },
       rules:{
         resourceName:[{required:true,message:'请输入资源名称',trigger:'blur'},
           {max:30,message:'资源名称长度应小于30',trigger:'blur'}],
         //resourceShowOrder:[{ max:999 , message: '显示顺序必须为数字值',trigger:'change'}],
         isMenu:[{required:true,message:'必须选择',trigger:'blur'}],
         authorizationToken:[{required:true,message:'请输入权限标识',trigger:'blur'},
           {max:50,message:'权限标识长度应小于50',trigger:'blur'}]
       },

       selectResource: {
         name: '',
         id: '',
       },
       totalTreeResource: [],
       show: false,
       defaultProps:{
         id:'resourceId',
         label:'resourceName',
         children:'children'
       },
       resourceName:'',
       loading:false,
       littleLoading:false
     }
   },

   mounted() {
     this.getResource();
   },

   methods: {
     back: function () {
       this.$router.push({path: '/resource'});
     },

     getResource: function () {
       this.littleLoading = true;
       var param = {resourceName:this.resourceName};
       resourceApi.getTotalResource(param).then((res) => {
         this.littleLoading = false;
         this.totalTreeResource = res.data.data;
       }).catch((message)=>{
         this.littleLoading = false;
         Msg.error(message);
       })
     },

     showResource: function () {
       this.show = true
     },

     save: function () {
     this.$refs['resourceInfo'].validate((valid)=>{
       if(valid){
         this.loading = true;
           var param =   this.resourceInfo;
           console.info(this.resourceInfo);
           resourceApi.addResourceInfo(param).then((res) => {
             if(res.status == '200'){
               this.loading = false;
               Msg.success(Tip.ADD_SUCCESS);
               this.$router.push({path: '/resource'});
             }
             else if(res.status == '500'){
               this.loading = false;
               Msg.error(Tip.ADD_ERROR);
               this.$router.push({path: '/resource'});
             }
           }).catch((message) => {
             this.loading = false;
             Msg.error(message);
           })
         }
       else{
         alert('请输入正确的内容');
       }
     })
     },
     ensure: function () {
       this.resourceInfo.parentResourceId =  this.selectResource.id;
       this.resourceInfo.parentName = this.selectResource.name;
       this.show = false;
     },
     close: function () {
       this.show = false;
     },
     handleNodeClick:function(data){
       this.selectResource.name = data.resourceName;
       this.selectResource.id = data.resourceId;
     },
    search: function(){
      this.getResource();
    }
   }
 }
</script>
