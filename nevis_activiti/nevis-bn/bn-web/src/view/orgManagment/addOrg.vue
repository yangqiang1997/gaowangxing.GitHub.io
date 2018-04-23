<template>
  <div>
    <el-form :model="formData" ref="formData" :rules="rules"  label-width="150px" class="left">
      <el-form-item label="组织机构名称" prop="organizationName">
        <el-input v-model="formData.organizationName" style="width: 250px"></el-input>
      </el-form-item>
      <el-form-item label="组织机构类型" prop="organizationTypeId">
        <el-select v-model="formData.organizationTypeId" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in optionOfCom"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否禁用" prop="isLock">
        <el-select v-model="formData.isLock" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否过期" prop="isExpired">
        <el-select v-model="formData.isExpired" placeholder="请选择" style="width: 250px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="sure('formData')" class="button1">确定</el-button>
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
        formData: {
          parentOrganizationId:null,
          organizationName: null,
          isLock: null,
          isExpired: null,
          isDelete: null,
          organizationTypeId: null,
        },
        optionOfCom: [],
        rules: {
          organizationName: [
            { required: true, message: '请输入组织机构名称', trigger: 'blur' },
            { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
          ],
          isLock: [
            { required: true, message: '请选择是否禁用', trigger: 'change' }
          ],
          organizationTypeId: [
            { required: true, message: '选择公司类型', trigger: 'change' }
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
        }],
      }
    },
    mounted() {
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
      sure: function (fromName) {
        this.formData.parentOrganizationId = this.$route.query.id;
        this.$refs[fromName].validate((valid) => {
          if (valid) {
            orgManagementApi.addOrgApi(this.formData).then(res => {
              console.log(res);
              if(res.status == '200'){
                if(res.data.data == true){
                  Msg.success(Tip.ADD_SUCCESS);
                }else{
                  Msg.error(Tip.ADD_ERROR);
                }
                this.$router.push({path:'./orgManage'});
              }else{
                Msg.error(Tip.ADD_ERROR);
              }
            });
          } else {
            return false;
          }
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
