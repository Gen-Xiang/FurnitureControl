<template>
  <div>
    <div class="nav">
      <div v-for="(item,index) in navList" :key="index" :class="index == active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
      </div>
    </div>
  <div>
    个人信息
    <br><br>
    uid: <span v-text="userinfo.uid"></span>
    <br><br>
    username: <span v-text="userinfo.username"></span>
    <br><br>
    email: <span v-text="userinfo.email"></span>
    <br><br>
    introduction: <span v-text="userinfo.introduction"></span>
    <br><br>
    <el-button type="primary" v-on:click="updateInfo">修改信息</el-button>
  </div>
<!--    <div style="height:600px">-->
<!--      <el-image class="img" width="100%" :src="require('@/assets/img/banner-1.jpg')" fit="cover">-->
<!--      </el-image>-->
<!--    </div>-->
<!--  </div>-->
<!--  <div>-->
<!--    Hello World!-->
  </div>
</template>

<script>
export default {
  name: "Home",
  data () {
    return {
      active: null,
      navList: [
        { name: '个人中心' },
        { name: '房间列表' },
        { name: '设备列表' },
        { name: '退出登录' },
      ],
      userinfo: {
        uid: '',
        username: '',
        password: '',
        email: '',
        introduction: ''
      }
    }
  },
  created () {
    this.active = this.$route.query.active
    this.$axios.post('/user',{username: "chx"}).then(successResponse => {
      this.userinfo.uid=successResponse.data.uid;
      this.userinfo.username=successResponse.data.username;
      this.userinfo.password=successResponse.data.password;
      this.userinfo.email=successResponse.data.email;
      this.userinfo.introduction=successResponse.data.introduction;
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
    updateInfo(){
      this.$router.replace({path: '/updateinfo'})
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
