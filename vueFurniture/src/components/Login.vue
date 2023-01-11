<template>
  <body id="poster">
  <el-form class="login-container" label-position="left">
    <h1 class="login_title">智能家居管理系统</h1>
    <el-form-item label="用户名">
      <el-input type="text" label-width="auto" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" label-width="auto" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" v-on:click="login">一键登录</el-button>
      <el-button type="info" v-on:click="register">注册账号</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      this.$axios
        .post('/user/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$message.success("登录成功");
            this.$router.replace({path: '/home'})
          }
          else if (successResponse.data.code === 400){
            this.$message.error("登录失败");
          }
        })
    },
    register(){
      this.$router.replace({path: '/register'})
    }

  }
}
</script>

<style scoped>
#poster {
  background:url("../assets/Nihida.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin:-9px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 200px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
