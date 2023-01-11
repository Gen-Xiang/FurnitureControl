<template>
  <body id="poster">
  <el-button type="info" style="margin-top: 50px; text-align: left" v-on:click="back">返回</el-button>
  <el-form class="register-container" label-position="left">
    <h1 class="register_title">注册新用户</h1>
    <el-form-item label="用户名">
      <el-input type="text" label-width="auto" v-model="registerForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" label-width="auto" v-model="registerForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item label="电子邮箱">
      <el-input type="text" label-width="auto" v-model="registerForm.email" auto-complete="off" placeholder="请输入电子邮箱"></el-input>
    </el-form-item>
    <el-form-item label="个人介绍">
      <el-input type="text" label-width="auto" v-model="registerForm.introduction" auto-complete="off" placeholder="请输入个人介绍"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" v-on:click="register">注册账号</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
export default {
  name: "Register",
  data () {
    return {
      registerForm: {
        username: '',
        password: '',
        email: '',
        introduction: ''
      },
      responseResult: []
    }
  },
  methods: {
    register() {
      this.$axios
        .post('/user/register', {
          username: this.registerForm.username,
          password: this.registerForm.password,
          email: this.registerForm.email,
          introduction: this.registerForm.introduction
        })
        .then(successResponse => {
          if (successResponse.data.uid > 0 ) {
            this.$message.success("创建成功，您的uid为"+successResponse.data.uid)
            this.$router.replace({path: '/login'})
          }
          else if (successResponse.data.uid === -1){
            this.$message.error("创建失败，用户名"+successResponse.data.username+"已存在")
          }
        })
    },
    back() {
      this.$router.replace({path: '/login'})
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
.register-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.register_title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
