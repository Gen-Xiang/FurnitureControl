<template>
  <div>
    <el-button type="info" v-on:click="back">返回</el-button>
    添加新设备
    <br><br>
    设备名:<input type="text" v-model="EquipForm.equipname" placeholder="请输入设备名"/>
    <br><br>
    房间rid： <input type="text" v-model="EquipForm.rid" placeholder="请输入房间rid"/>
    <br><br>
    设备类型： <el-select v-model="EquipForm.type" placeholder="请选择设备类型">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    <br><br>
    <el-button type="primary" v-on:click="add">添加设备</el-button>
  </div>
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
        }
        else if (successResponse.data.uid === -1){
          this.$message.error("创建失败")
        }
      })
    }
  }


}
</script>

<style scoped>

</style>
