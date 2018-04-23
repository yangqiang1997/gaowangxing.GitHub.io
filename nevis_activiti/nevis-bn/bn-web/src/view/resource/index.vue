<template>
  <div class="app-container" v-loading="loading">
    <div class="start">
     <span>菜单名称:</span>
     <el-input v-model="name" placeholder="请输入菜单名称" style="width:40%" @keyup.enter.native="search"></el-input>
     <el-button size="primary" @click="search"  >搜索</el-button>
     <el-button size="primary" @click="newAdd" style="float:right"  v-loading="loading"v-auth="{id:'RESOURCE_ADD'}">新增</el-button>
    </div>

    <tree-table :data="sourceTreeList" :evalFunc="func" :evalArgs="args" :expandAll="expandAll" border>
      <el-table-column label="菜单名称" width="200">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.resourceName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="路由地址">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.resourcePath}}</span>
        </template>
      </el-table-column>
      <el-table-column label="排序">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.resourceShowOrder}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否为菜单">
        <template slot-scope="scope">
          <span style="color:sandybrown" v-if='scope.row.isMenu '>是</span>
          <span style="color:sandybrown" v-if='!scope.row.isMenu '>否</span>
        </template>
      </el-table-column>
      <el-table-column label="权限标识">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.authorizationToken}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="280">
        <template slot-scope="scope">
          <el-button size="mini"   @click="edit(scope.row.resourceId)"  v-auth="{id:'RESOURCE_UPDATE'}">编辑</el-button>
          <el-button size="mini" type="danger" v-loading="" @click="del(scope.row.resourceId)"  v-auth="{id:'RESOURCE_DELETE'}">删除</el-button>
          <el-button size="mini"  @click="addNextLevel(scope.row.resourceId)" >新增下一级</el-button>
        </template>
      </el-table-column>
    </tree-table>
  </div>
</template>

<style lang="scss" scoped>
.start{
  width:100%;
  height:20%;
  margin-bottom: 20px;
}
</style>

<script>

  import treeTable from './TreeTable'
  import treeToArray from './customEval'
  import {resourceApi} from './resource-api';
  import {Tip} from '../../tools/systemConstants'
  import {Msg} from '@/tools/message'

  export default {
  name: 'customTreeTableDemo',
  components: { treeTable },
  data() {
    return {
      func: treeToArray,
      expandAll: false,
      sourceTreeList:[],
      name:'',
      loading:false,
      args: [null, null, 'timeLine']
    }
  },
  mounted(){
    this.getSourceList()
  },
  methods: {
    getSourceList: function () {
      this.loading = true;
      var param={resourceName:this.name};
      resourceApi.getSourceList(param).then((res) => {
        this.loading = false;
        this.sourceTreeList = res.data.data;
      }).catch((message)=>{
        this.loading = false;
        Msg.error(message);
      })
    },
    search:function(){
      this.getSourceList();
    },
    newAdd:function(){
      this.$router.push({path:'/resourceAdd'});
    },
    edit:function(id){
      this.$router.push({path:'/resourceEdit',query:{resourceId:id}});
    },
    del:function(id){
      this.$confirm('此操作将删除此资源，是否继续？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(()=>{
        var param ={resourceId:id};
        this.loading = true;
        resourceApi.delData(param).then((res)=>{
          this.getSourceList();
          this.loading = false;
          Msg.success(Tip.DEL_SUCCESS);
        }).catch((message) =>{
          this.loading = false;
          Msg.error(message);
        })
      }).catch(()=>{
         this.$message({
           type:'info',
           message:'已取消删除'
         })
      })
    },
    addNextLevel:function(id){
      this.$router.push({path:'/resourceAddNext',query:{resourceId:id}});
    }
  }
}
</script>
