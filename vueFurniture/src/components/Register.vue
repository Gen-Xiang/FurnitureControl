<template>
  <div>
    <button v-on:click="back">返回</button>
    注册新账号
    <br><br>
    用户名:<input type="text" v-model="registerForm.username" placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" v-model="registerForm.password" placeholder="请输入密码"/>
    <br><br>
    邮箱： <input type="text" v-model="registerForm.email" placeholder="请输入邮箱"/>
    <br><br>
    个人简介： <input type="text" v-model="registerForm.introduction" placeholder="请输入简介"/>
    <br><br>
    <button v-on:click="register">注册账号</button>
  </div>
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
        .post('/register', {
          username: this.registerForm.username,
          password: this.registerForm.password,
          email: this.registerForm.email,
          introduction: this.registerForm.introduction
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$router.replace({path: '/login'})
          }
        })
        // .catch(failResponse => {
        // })
    },
    back() {
      this.$router.replace({path: '/login'})
    }
  }
}
</script>

<style scoped>

</style>
