<template>
  <body id="poster">
    <div class="nav">
      <div v-for="(item,index) in navList" :key="index" :class="index === active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
      </div>
    </div>
    <el-button class="button" type="primary" v-on:click="addEquipment">添加设备</el-button>
    <el-table
      :data="tableData"
      :border=true
      :default-sort = "{prop: 'rid', order: 'ascending'}"
      style="width: 70%;height: 100%;margin-top: 20px">
      <el-table-column
        prop="rid"
        label="房间rid"
        width="200">
      </el-table-column>
      <el-table-column
        prop="eid"
        label="eid"
        sortable
        width="200">
      </el-table-column>
      <el-table-column
        prop="equipname"
        label="设备名"
        width="200">
      </el-table-column>
      <el-table-column
        prop="type"
        label="图片"
        width="270">
        <template slot-scope="scope">
          <div v-if="scope.row.type===1">
            <el-avatar shape="square" :size="100" :src="require('../assets/lamp.jpeg')"></el-avatar>
          </div>
          <div v-else-if="scope.row.type===2">
            <el-avatar shape="square" :size="100" :src="require('../assets/switch.jpeg')"></el-avatar>
          </div>
          <div v-else-if="scope.row.type===3">
            <el-avatar shape="square" :size="100" :src="require('../assets/sensor.jpeg')"></el-avatar>
          </div>
          <div v-else-if="scope.row.type===4">
            <el-avatar shape="square" :size="100" :src="require('../assets/lock.jpeg')"></el-avatar>
          </div>
          <div v-else>error type</div>
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        label="操作"
        width="300">
        <template slot-scope="scope">
          <div v-if="scope.row.type===1">
            开关：
            <el-switch
              v-model="scope.row.status"
              @change="changeStatus(scope.row.eid,scope.row.status)"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
            <br><br>
            亮度：
            <el-slider style="width: 100%" v-model="scope.row.luminance" @change="changeLuminance(scope.row.eid,scope.row.luminance)"></el-slider>
            <br>
            <el-button class="button2" type="danger" v-on:click="deleteEquipment(scope.row.eid)">删除设备</el-button>
          </div>
          <div v-else-if="scope.row.type===2">
            开关：
            <el-switch
              v-model="scope.row.status"
              @change="changeStatus(scope.row.eid,scope.row.status)"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
            <br>
            <el-button class="button2" type="danger" v-on:click="deleteEquipment(scope.row.eid)">删除设备</el-button>
          </div>
          <div v-else-if="scope.row.type===3">
            温度：<span v-text="scope.row.temperature"></span>
            <br><br>
            湿度：<span v-text="scope.row.humidity"></span>
            <br>
            <el-button class="button2" type="danger" v-on:click="deleteEquipment(scope.row.eid)">删除设备</el-button>
          </div>
          <div v-else-if="scope.row.type===4">
            门锁：
            <el-switch
              v-model="scope.row.status"
              @change="changeLockStatus(scope.row.eid,scope.row.status)"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
            <br>
            <el-button class="button2" type="danger" v-on:click="deleteEquipment(scope.row.eid)">删除设备</el-button>
          </div>
          <div v-else>error type</div>
        </template>
      </el-table-column>
    </el-table>
  </body>
</template>

<script>
export default {
  name: "EquipmentList",
  data () {
    return {
      active: null,
      userinfo:{},
      navList: [
        { name: '个人中心' },
        { name: '房间列表' },
        { name: '设备列表' },
        { name: '退出登录' },
      ],
      tableData:[]
    }
  },
  created () {
    this.active = this.$route.query.active
    this.$axios.post('/user/logined').then(successResponse => {
      this.userinfo=successResponse.data
      this.$axios.post('/equipment',{uid: this.userinfo.uid}).then(successResponse => {
        this.tableData=successResponse.data
      })
    })
  },
  methods: {
    toPage (e) {
      console.log(e, 'e')
      switch (e) {
        case 0:
          this.$router.push({ path: '/home', query: { active: this.active } })
          break
        case 1:
          this.$router.push({ path: '/roomlist', query: { active: this.active } })
          break
        case 2:
          this.$router.push({ path: '/equipmentlist', query: { active: this.active } })
          break
        case 3:
          this.$router.push({ path: '/login', query: { active: this.active } })
          this.$message.success("退出登录成功")
          break

      }
    },
    addEquipment(){
      this.$router.push({path:'/addequipment'})
    },
    deleteEquipment(eid){
      this.$confirm('此操作将永久删除该设备, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete("/equipment/"+eid)
        this.$message.success('删除成功!');
        window.location.reload()
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    },
    changeStatus(eid,status){
      this.$axios.
        put("/equipment/status/"+eid,{
          status: status
      })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            console.log("开关状态为"+status)
          }
        })
    },
    changeLuminance(eid,luminance){
      this.$axios.
      put("/equipment/luminance/"+eid,{
        luminance: luminance
      })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            console.log("亮度为"+luminance)
          }
        })
    },
    changeLockStatus(eid,status){
      this.$axios.
      put("/equipment/status/"+eid,{
        status: status
      })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            if (status){
              this.$message.success("eid为"+eid+"的门锁已关锁")
            }
            else{
              this.$message.warning("eid为"+eid+"的门锁已开锁")
            }
          }
        })
    },
  }
}
</script>

<style scoped>

.nav {
  width: 100%;
  display: flex;
  justify-content: space-around;
  z-index: 9;
  position: absolute;
  top: 20px;
  font-size: 28px;
  cursor: pointer;
}

.active {
  background: #486341;
  color: #ffffff;
// padding: 0 5px;
//min-width: 120px;
}

#poster {
  background:url("../assets/Nihida2.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
}
body{
  margin:-9px;
}

.button{
  margin-top: 60px;
  text-align: left;
}
.button2{
  margin-top: 5px;
  text-align: center;
}
</style>
