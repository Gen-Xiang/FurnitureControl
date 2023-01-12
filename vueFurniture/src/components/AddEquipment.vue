<template>
  <body id="poster">
    <el-button type="info" style="margin-top: 50px; text-align: left" v-on:click="back">返回</el-button>
    <el-form class="addequipment-container" label-position="left">
      <h1 class="addequipment_title">添加新设备</h1>
      <el-form-item label="设备名">
        <el-input type="text" label-width="auto" v-model="EquipForm.equipname" auto-complete="off" placeholder="请输入设备名"></el-input>
      </el-form-item>
      <el-form-item label="房间rid">
        <el-input type="text" label-width="auto" v-model="EquipForm.rid" auto-complete="off" placeholder="请输入房间rid"></el-input>
      </el-form-item>
      <el-form-item label="设备类型">
        <el-select v-model="EquipForm.type" placeholder="请选择设备类型">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" v-on:click="add">添加设备</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: "AddEquipment",
  data () {
    return {
      userinfo:{},
      EquipForm:{
        rid: 0,
        equipname: '',
        type: 0
      },
      options: [{
        value: 1,
        label: '灯'
      }, {
        value: 2,
        label: '开关'
      }, {
        value: 3,
        label: '传感器'
      }, {
        value: 4,
        label: '门锁'
      }]
    }
  },
  created() {
    this.$axios.post('/user/logined').then(successResponse => {
      this.userinfo=successResponse.data
    })
  },
  methods:{
    back(){
      this.$router.replace({path: '/equipmentlist'})
    },
    add(){
      if (this.EquipForm.rid===0){
        this.$message.error("房间rid不能为0")
      }
      else if (this.EquipForm.equipname.length===0){
        this.$message.error("设备名不能为空")
      }
      else if (this.EquipForm.type===0){
        this.$message.error("请选择设备类型")
      }
      else{
        this.$axios.
        post("/equipment/add",{
          uid: this.userinfo.uid,
          rid: this.EquipForm.rid,
          equipname: this.EquipForm.equipname,
          type: this.EquipForm.type
        })
          .then(successResponse => {
            if (successResponse.data.eid > 0 ) {
              this.$message.success("创建成功，新设备的eid为"+successResponse.data.eid)
              this.$router.replace({path: '/equipmentlist'})
              window.location.reload()
            }
            else if (successResponse.data.eid === -1){
              this.$message.error("设备名已存在")
            }
            else if (successResponse.data.eid === -2){
              this.$message.error("rid为"+successResponse.data.rid+"的房间不存在")
            }
          })
      }
      }
  }


}
</script>

<style scoped>
#poster {
  background:url("../assets/Nihida2.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin:-9px;
}
.addequipment-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 30px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.addequipment_title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
