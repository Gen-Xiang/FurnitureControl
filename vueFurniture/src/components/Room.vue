<template>
  <div>
    <div class="nav">
      <div v-for="(item,index) in navList" :key="index" :class="index == active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
      </div>
    </div>
    <el-button type="info" v-on:click="back">返回</el-button>
    <br><br>
    <el-avatar shape="square" :size="1000" :fit="fit" :src="require('../assets/'+roomForm.graph)"></el-avatar>
  </div>
</template>

<script>
export default {
  name: "Room",
  data () {
    return {
      rid: 0,
      active: null,
      navList: [
        { name: '个人中心' },
        { name: '房间列表' },
        { name: '设备列表' },
        { name: '退出登录' },
      ],
      roomForm: {}
    }
  },
  created() {
    this.rid=this.$route.params.rid;
    this.active = this.$route.query.active
    this.$axios.post('/room/getroom',{rid: this.rid}).then(successResponse => {
      this.roomForm=successResponse.data
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
          this.$router.push({ path: '/register', query: { active: this.active } })
          break
        case 3:
          this.$router.push({ path: '/login', query: { active: this.active } })
          break

      }
    },
    back(){
      this.$router.push({path: '/roomlist'})
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
