<template>
  <div>
    <el-form :model="formData" ref="formData" :rules="rules"  label-width="80px" class="left" v-loading="loading">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="formData.roleName" style="width: 250px"></el-input>
      </el-form-item>

      <el-form-item label="角色类型" prop="roleType">
        <el-select v-model="formData.roleType" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in roleTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="允许修改" prop="isAllowEdit">
        <el-select v-model="formData.isAllowEdit" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="允许删除" prop="isAllowDelete">
        <el-select v-model="formData.isAllowDelete" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-button type="primary" @click="sure('formData')" class="button1">确定</el-button>
      <el-button type="primary" @click="$router.push('/roleManagement')" class="button2">返回</el-button>

    </el-form>
    <br/>
  </div>
</template>

<script>

  import {RoleManagementApi} from './api'
  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  import {ResponseCode} from '../../tools/ResponseCode'

  export default{
    name : 'modifyRole',
    mounted() {
      this.getTableData();
    },
    data(){
      return{
        loading: false,
        id: this.$route.query.id,
        formData: {
          id: this.$route.query.id,
          roleName: null,
          isAllowEdit: null,
          isAllowDelete: null,
          roleType: null
        },
        rules: {
          roleName: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'blur' }
          ],
          isAllowEdit: [
            { required: true, message: '请选择是否允许修改', trigger: 'change' }
          ],
          isAllowDelete: [
            { required: true, message: '请选择是否允许删除', trigger: 'change' }
          ],
          roleType: [
            { required: true, message: '请选择角色类型', trigger: 'change' }
          ]
        },
        options: [{
          value: null,
          label: '请选择'
        },{
          value: 1,
          label: '是'
        }, {
          value: 0,
          label: '否'
        }],
        roleTypeOptions: [{
          value: null,
          label: '请选择'
        },{
          value: 'Sale',
          label: '销售'
        }, {
          value: 'CustomerService',
          label: '客服'
        }]
      }
    },
    methods: {
      sure: function (fromName) {
        this.$refs[fromName].validate((valid) => {
          if (valid) {
            this.loading = true;
            RoleManagementApi.UpdateARole(this.formData).then(res => {
              if(res.data.code == ResponseCode.SUCCESS){
                Msg.success(Tip.EDIT_SUCCESS);
                this.$router.push('/roleManagement');
              }else{
                Msg.error(Tip.EDIT_ERROR);
              }
              this.loading = false;
            }).catch(err => {
              Msg.error(Tip.EDIT_ERROR);
              this.loading = false;
            });
          }
        });
      },
      getTableData: function () {
        this.loading = true;
        RoleManagementApi.GetARole(this.id).then(res => {
          if(res.data.data.allowDelete){
            this.formData.isAllowDelete = 1;
          }else {
            this.formData.isAllowDelete = 0;
          }
          if(res.data.data.allowEdit){
            this.formData.isAllowEdit = 1;
          }else {
            this.formData.isAllowEdit = 0;
          }
          this.formData.roleName = res.data.data.roleName;
          this.formData.roleType = res.data.data.roleType;
          this.loading = false;
        }).catch(err => {
          Msg.error(Tip.SERVER_ERROR);
          this.loading = false;
        });
      }
    }
  }

</script>

<style lang="scss" scoped>
  .button1{
    margin-left: 85px;
    width: 100px;
    margin-top: 30px;
  }
  .button2 {
    margin-left: 40px;
    margin-right: 10px;
    width: 100px;
    margin-top: 30px;
  }
  .left{
    margin-top: 30px;
  }
</style>
