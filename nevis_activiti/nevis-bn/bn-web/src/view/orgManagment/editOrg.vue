<template>
  <div>
    <el-form :model="orgInfo" ref="orgInfo" :rules="rules"  label-width="150px" class="left">
      <el-form-item label="组织机构名称" prop="organizationName">
        <el-input v-model="orgInfo.organizationName" style="width: 250px"></el-input>
      </el-form-item>
      <el-form-item label="组织机构类型" prop="organizationTypeId">
        <el-select v-model="orgInfo.organizationTypeId" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in optionOfCom"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否禁用" prop="isLock">
        <el-select v-model="orgInfo.isLock" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否过期" prop="isExpired">
        <el-select v-model="orgInfo.isExpired" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="save('orgInfo')" class="button1">保存</el-button>
      <el-button type="primary" @click="$router.push({path:'./orgManage'})" class="button2">返回</el-button>

    </el-form>
    <br/>

  </div>
</template>

<script>

  import {Msg} from '../../tools/message'
  import {Tip} from '../../tools/systemConstants'
  import {orgManagementApi} from './orgManagementApi'
  export default{
    data(){
      return{
        orgInfo: {
          organizationName: null,
          isLock: null,
          isExpired: null,
          organizationId:this.$route.query.id,
          organizationTypeId: null,
        },
        optionOfCom: [],
        rules: {
          organizationName: [
            { required: true, message: '请输入组织机构名称', trigger: 'blur' },
            { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
          ],
          organizationTypeId: [
            { required: true, message: '选择公司类型', trigger: 'change' }
          ],
          isLock: [
            { required: true, message: '请选择是否禁用', trigger: 'change' }
          ],
          isExpired: [
            { required: true, message: '请选择是否过期', trigger: 'change' }
          ]
        },
        options: [{
          value: 1,
          label: '是'
        }, {
          value: 0,
          label: '否'
        }]
      }
    },
    mounted() {
      this.getOrgInfo();
      this.getOrgType();
    },
    methods: {
      test: function () {
        console.log(this.$route.query.id);
      },
      getOrgType:function () {
        this.optionOfCom.push();
        const  param = {withChildren:1};/*const定义的变量只能赋值一次*/
        orgManagementApi.GetOrgType().then(res =>{
          let option = res.data.data;
          for(var i = 0; i < option.length;i++) {
            let param = {value:option[i].organizationTypeId, label:option[i].organizationTypeName};
            this.optionOfCom.push(param);
          }
        }).catch((message) => {
          Msg.error(message);
        })
      },
      getOrgInfo: function () {
        var id = this.$route.query.id;
        var param = {id: id };
        orgManagementApi.getOrgInfo(param).then(res => {
          console.log(res.data.data);
          if(res.data.data.isLock) {
            this.orgInfo.isLock = 1;
          }
          else{
            this.orgInfo.isLock = 0;
          }
          if(res.data.data.isExpired) {
            this.orgInfo.isExpired = 1;
          }
          else{
            this.orgInfo.isExpired = 0;
          }
          this.orgInfo.organizationName = res.data.data.organizationName;
          this.orgInfo.organizationTypeId = res.data.data.organizationTypeId;
        })
      },
      save: function (orgInfo) {
        this.$refs[orgInfo].validate((valid) => {
          if (valid) {
            orgManagementApi.updateOrgInfo(this.orgInfo).then(res => {
              if (res.status == '200') {
                if(res.data.data == true){
                  Msg.success(Tip.EDIT_SUCCESS);
                }else{
                  Msg.error(Tip.EDIT_ERROR);
                }
                this.$router.push({path: '/orgManage'});
              }
              else if (res.status == '500') {
                Msg.error(Tip.EDIT_ERROR)
                this.$router.push({path: '/orgManage'});
              }
            }).catch(err => {
              Msg.error(Tip.EDIT_ERROR)
              console.log(err);
            })
          }
          else {
            alert('请输入正确的内容');
          }
        })
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
