<template>

  <div>
    <h1>数据范围 - {{data.roleName}}</h1>
    <br/>

    <el-tree
      :data="treeData"
      show-checkbox
      node-key="id"
      :props="defaultProps"
      ref="tree"
      v-loading="loading"
      @check-change = "checkNode">
    </el-tree>
    <br/>
    <el-button type="primary" @click="sure()" :loading="loading" class="button1" >确定</el-button>
    <el-button type="primary" @click="$router.push('/roleManagement')" class="button2">返回</el-button>
  </div>

</template>

<script>

  import {RoleManagementApi} from './api'
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  import {ResponseCode} from '../../tools/ResponseCode'

  export default{
    name : 'distributionDataScope',
    data(){
      return{
        loading: false,
        data: this.$route.query.data,
        treeData: [{
          id: 'ALL',
          label: '所有数据'
        }, {
          id: 'COMPANY_AND_CHILD',
          label: '公司及以下数据'
        },{
          id: 'COMPANY',
          label: '公司数据'
        },{
          id: 'DEPARTMENT_AND_CHILD',
          label: '部门及以下数据'
        },{
          id: 'DEPARTMENT',
          label: '部门数据'
        },{
          id: 'SELF',
          label: '个人数据'
        },{
          id: 'CUSTOM',
          label: '自定义数据',
          children: null
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    mounted() {
      this.getTreeData();
    },
    methods: {
      checkNode: function(data, checked){
        var source = [].concat(this.$refs.tree.getHalfCheckedKeys(), this.$refs.tree.getCheckedKeys());
        if(checked && (this.ifFathurNode(data.id) || this.ifHaveTwoNode(source))){
          this.$refs.tree.setCheckedKeys([data.id]);
        }
      },
      ifHaveTwoNode: function (data) {
          var count = 0;
        for(var i = 0 ; i < data.length ; i++){
          if(this.ifFathurNode(data[i])) count++;
        }
        if(count >= 2 ) return true;
      },
      ifFathurNode: function(id){
          for(var i = 0 ; i < this.treeData.length ; i++){
            if(this.treeData[i].id == id) return true;
          }
          return false;
      },
      sure: function (fromName) {
        //this.loading = true;
        var dataScope = [].concat(this.$refs.tree.getHalfCheckedKeys(), this.$refs.tree.getCheckedKeys());
        var checked = '';
        for(var i = 0 ; i < dataScope.length ; i++){
          if(i < dataScope.length-1){
            checked += (dataScope[i] + ',');
          }else {
            checked += dataScope[i];
          }
        }

        if(dataScope.length == 0){
          Msg.error(Tip.LEAST_ONE_CHOICE);
        }else{
          RoleManagementApi.SetDataScope({checked: checked,
            id: this.data.roleId}).then(res => {
            if(res.data.code == ResponseCode.SUCCESS){
              Msg.success(Tip.SET_SUCCESS);
              this.$router.push('/roleManagement');
            }else{
              Msg.error(Tip.SET_ERROR);
            }
            this.loading = false;
          }).catch(err => {
            Msg.error(Tip.SET_ERROR);
            this.loading = false;
          });
        }
      },
      getTreeData: function () {
        this.loading = true;
        RoleManagementApi.GetAllOrgs().then(res => {
          this.treeData[6].children = res.data.data;

            RoleManagementApi.GetDataScope(this.data.roleId).then(res => {
              console.log(res.data.data);
              if(res.data.data != null){
                this.$refs.tree.setCheckedKeys(res.data.data)
              }
              this.loading = false;
            }).catch(err => {
              Msg.error(Tip.SERVER_ERROR);
              this.loading = false;
            });

          this.loading = false;
        }).catch(err => {
          Msg.error(Tip.SERVER_ERROR);
        });
      }
    }
  }

</script>

<style lang="scss" scoped>
  .button1 {
    margin-left: 20%;
    width: 100px
  }
  .button2 {
    margin-left: 40px;
    margin-right: 10px;
    width: 100px;
  }
</style>
