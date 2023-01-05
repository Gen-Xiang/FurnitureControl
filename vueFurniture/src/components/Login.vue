<template>
  <div>
    用户名:<input type="text" v-model="loginForm.username" placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" v-model="loginForm.password" placeholder="请输入密码"/>
    <br><br>
    <button v-on:click="login">一键登录</button>
    <button v-on:click="register">注册账号</button>
  </div>
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
            this.$message("登录成功");
            this.$router.replace({path: '/home'})
          }
          else if (successResponse.data.code === 400){
            this.$message.error("登录失败");
          }
        })
        // .catch(failResponse => {
        //   // this.$message.warning("请求失败")
        // })
    },
    register(){
      this.$router.replace({path: '/register'})
    }

  }
}
</script>

<style scoped>

</style>
