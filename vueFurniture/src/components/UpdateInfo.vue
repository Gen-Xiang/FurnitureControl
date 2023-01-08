<template>
  <div>
    <el-button type="info" v-on:click="back">返回</el-button>
    <br><br>
    修改个人信息
    <br><br>
    用户名:<input type="text" v-model="updateForm.username" placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" v-model="updateForm.password" placeholder="请输入密码"/>
    <br><br>
    邮箱： <input type="text" v-model="updateForm.email" placeholder="请输入邮箱"/>
    <br><br>
    个人简介： <input type="text" v-model="updateForm.introduction" placeholder="请输入简介"/>
    <br><br>
    <button v-on:click="update">提交修改</button>
  </div>
</template>

<script>
export default {
  name: "UpdateInfo",
  data () {
    return {
      updateForm: {
        uid: 1,
        username: '',
        password: '',
        email: '',
        introduction: ''
      },
      responseResult: []
    }
  },
  methods: {
    update() {
      this.$axios
        .put('/user/'+this.updateForm.uid.toString(), {
          username: this.updateForm.username,
          password: this.updateForm.password,
          email: this.updateForm.email,
          introduction: this.updateForm.introduction
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$message.success("修改成功")
            this.$router.replace({path: '/home'})
          }
        })
        // .catch(failResponse => {
        // })
    },
  back() {
    this.$router.replace({path: '/home'})
  }
  }
}
</script>

<style scoped>

</style>
