<template>
  <body id="poster">
    <div class="nav">
      <div v-for="(item,index) in navList" :key="index" :class="index === active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
      </div>
    </div>
    <el-button class="button" type="primary" v-on:click="addRoom">添加房间</el-button>
    <el-table
      :data="tableData"
      :border=true
      :default-sort = "{prop: 'rid', order: 'ascending'}"
      style="width: 80%;height: 100%;margin-top: 20px">
      <el-table-column
        prop="rid"
        label="rid"
        sortable
        width="300">
      </el-table-column>
      <el-table-column
        prop="roomname"
        label="房间名"
        width="300">
      </el-table-column>
      <el-table-column
        prop="graph"
        label="图片"
        width="400">
        <template slot-scope="scope">
          <el-avatar shape="square" :size="200" :src="require('../assets/'+scope.row.graph)"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="338">
        <template slot-scope="scope">
          <el-button @click="handleLook(scope.row.rid)" type="primary" >查看</el-button>
          <el-button @click="handleDelete(scope.row.rid)" type="danger" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </body>
</template>

<script>
export default {
  name: "RoomList",
  data () {
    return {
      active: null,
      userinfo:{},
      fit: 'fill',
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
    this.$axios.post('/user/logined').then(successResponse => {
      this.userinfo=successResponse.data
      this.$axios.post('/room',{uid: this.userinfo.uid}).then(successResponse => {
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
          break
      }
    },
    addRoom(){
      this.$router.push({path: '/addroom'})
    },
    handleLook(rid){
      this.$router.push({path: '/room/'+rid})
    },
    handleDelete(rid){
      this.$confirm('此操作将永久删除该设备, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete('room/'+rid)
        this.$message.success("删除成功")
        window.location.reload()
      }).catch(() => {
        this.$message.info('已取消删除');
      });
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
</style>
