<template>
  <body id="poster">
  <div class="nav">
    <div v-for="(item,index) in navList" :key="index" :class="index === active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
    </div>
  </div>
  <el-form class="home-container" label-position="left" >
    <h3 class="home_title">个人信息</h3>
    <el-form-item label="uid">
      <span class="word" v-text="this.userinfo.uid"></span>
    </el-form-item>
    <el-form-item label="用户名">
      <span class="word" v-text="this.userinfo.username"></span>
    </el-form-item>
    <el-form-item label="电子邮箱">
      <span class="word" v-text="this.userinfo.email"></span>
    </el-form-item>
    <el-form-item label="个人介绍">
      <span class="word" v-text="this.userinfo.introduction"></span>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" v-on:click="updateInfo">修改信息</el-button>
    </el-form-item>
  </el-form>
  </body>
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
      }
    }
  },
  created () {
    this.active = this.$route.query.active
    this.$axios.post('/user/logined').then(successResponse => {
      this.userinfo=successResponse.data
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
    position: fixed;
  }
  body{
    margin:-9px;
  }
  .home-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 200px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .home_title {
    margin: 0 auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .word {
    font-size: 20px;
  }
</style>
