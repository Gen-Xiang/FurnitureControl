<template>
  <div>
    <div class="nav">
      <div v-for="(item,index) in navList" :key="index" :class="index == active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
      </div>
    </div>
<!--    <el-row>-->
<!--      <el-col :span="12">&nbsp</el-col>-->
<!--      <el-col :span="12">&nbsp</el-col>-->
<!--    </el-row>-->
    <el-row>
      <el-col :span="24"><div>
        <el-table
          :data="tableData"
          :border=true
          :default-sort = "{prop: 'rid', order: 'ascending'}"
          style="width: 100%;height: 100%">
          <el-table-column
            prop="rid"
            label="rid"
            sortable
            width="180">
          </el-table-column>
          <el-table-column
            prop="roomname"
            label="房间名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="graph"
            label="图片"
            width="480">
            <template slot-scope="scope">
              <el-avatar shape="square" :size="200" :src="require('../assets/'+scope.row.graph)"></el-avatar>
<!--              <el-image :src="require(scope.row.graph)" fit="contain" style="width: 200px;height: 200px"/>-->
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="300">
            <template slot-scope="scope">
              <el-button @click="handleLook(scope.row.rid)" type="primary" >查看</el-button>
<!--              <el-button type="info" >编辑</el-button>-->
              <el-button @click="handleDelete(scope.row.rid)" type="danger" >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div></el-col>
    </el-row>
    <el-button type="primary" v-on:click="addRoom">添加房间</el-button>
  </div>
</template>

<script>
export default {
  name: "RoomList",
  data () {
    return {
      active: null,
      uid: 1,
      fit: 'fill',
      url: '../assets/1.jpg',
      navList: [
        { name: '个人中心' },
        { name: '房间列表' },
        { name: '设备列表' },
        { name: '退出登录' },
      ],
      tableData: []
    }
  },
  created () {
    this.active = this.$route.query.active
    this.$axios.post('/room',{uid: this.uid}).then(successResponse => {
      this.tableData=successResponse.data
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
          break
      }
    },
    addRoom(){
      this.$router.push({path: '/addroom/'+this.uid})
    },
    handleLook(rid){
      // this.$message.success("room rid is "+rid)
      this.$router.push({path: '/room/'+rid})
    },
    handleDelete(rid){
      this.$axios.delete('room/'+rid)
      this.$message.success("删除成功")
      window.location.reload()
    }
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
  font-size: 20px;
  cursor: pointer;
}

.active {
  background: #486341;
  color: #ffffff;
// padding: 0 5px;
//min-width: 120px;
}
</style>
