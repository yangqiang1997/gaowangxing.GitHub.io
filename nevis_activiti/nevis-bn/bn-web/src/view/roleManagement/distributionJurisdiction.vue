<template>

  <div>
    <h1>分配权限 - {{data.roleName}}</h1>
    <br/>

    <el-tree
      :data="treeData"
      show-checkbox
      node-key="id"
      default-expand-all
      :props="defaultProps"
      ref="tree"
      v-loading="loading">
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
    name : 'distributionSource',
    data(){
      return{
        loading: false,
        data: this.$route.query.data,
        treeData: null,
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
      sure: function (fromName) {
        this.loading = true;
        var source = [].concat(this.$refs.tree.getHalfCheckedKeys(), this.$refs.tree.getCheckedKeys());
        var checked = '';

        for(var i = 0 ; i < source.length ; i++){
          if(i < source.length-1){
            checked += (source[i] + ',');
          }else {
            checked += source[i];
          }
        }
        RoleManagementApi.RoleJurisdiction({checked: checked,
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
      },
      getTreeData: function () {
        this.loading = true;
        RoleManagementApi.GetSourceListApi().then(res => {
            if(res.data.data != null){
              this.treeData = res.data.data;
            }
            RoleManagementApi.GetARoleJurisdiction(this.data.roleId).then(res => {
              if(res.data.data != null){
                this.$refs.tree.setCheckedKeys(res.data.data)
              }
              this.loading = false;
            }).catch(err => {
              Msg.error(Tip.SERVER_ERROR);
              this.loading = false;
            });
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
