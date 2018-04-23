<template>
  <div class="app-container">
    <el-button type="primary" style="margin-bottom: 1%" @click=newAdd>新增</el-button>
    <el-button type="warning" style="margin-bottom: 1%" @click="$router.push({path:'./orgManagment'})">返回</el-button>
    <tree-table :data="orgData" :columns="columns" border>
      <el-table-column label="组织机构名称">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.organizationName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="组织机构类型">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.organizationTypeName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="组织机构Id">
        <template slot-scope="scope">
          <span style="color:sandybrown">{{scope.row.organizationId}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否禁用">
        <template slot-scope="scope">
          <span style="color:sandybrown" v-if='scope.row.lock '>是</span>
          <span style="color:sandybrown" v-if='!scope.row.lock '>否</span>
        </template>
      </el-table-column>
      <el-table-column label="是否过期">
        <template slot-scope="scope">
          <span style="color:sandybrown" v-if='scope.row.expired '>是</span>
          <span style="color:sandybrown" v-if='!scope.row.expired '>否</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-button type="text" @click="edit(scope.row.organizationId)">编辑</el-button>
          <el-button type="text" v-loading="" @click="del(scope.row.organizationId)">删除</el-button>
          <el-button type="text" @click="addNextLevel(scope.row.organizationId)">新增下级</el-button>
        </template>
      </el-table-column>
    </tree-table>
    <br/>
  </div>
</template>

<script>
  /**
   Auth: lijie
   Created: 2018/4/15-09:54
   */
  import treeTable from '@/components/TreeTable'
  import {orgManagementApi} from './orgManagementApi'
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  export default {
    name: 'treeTableDemo',
    components: { treeTable },
    data() {
      return {
        orgData: []
      }
    },
    mounted() {
      this.getTreeTableData();
    },
    methods: {
      getTreeTableData: function () {
        orgManagementApi.GetSourceListApi().then(res => {
          console.log(res);
          if (res.data.data == null) {
          } else {
            this.orgData = res.data.data;
              console.log(this.orgData);
            }
        });
      },
      del: function (id) {
        this.$confirm('此操作将永久删除该组织机构及子组织机构, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          loading:true
        }).then(() => {
          var param ={organizationId:id};
          orgManagementApi.DeleteAOrg(param).then(res => {
            if (res.status == '200') {
              this.getTreeTableData();
              Msg.success(Tip.DEL_SUCCESS);
            } else {
              Msg.error(Tip.DEL_EROOR);
            }
          });
        }).catch(() => {
        });
      },
      newAdd:function(){
        this.$router.push({path:'/addOrg'});
      },
      edit:function(organizationId){
        this.$router.push({path:'/editOrg',query:{id:organizationId}});
      },
      addNextLevel: function (organizationId) {
        this.$router.push({path: '/addOrg', query:{id: organizationId}});
      }
    }
  }
</script>
