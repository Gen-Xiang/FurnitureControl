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
    <el-button type="primary" v-on:click="update">提交修改</el-button>
    <br><br>
    <el-button type="danger" v-on:click="deleteUser">注销账户</el-button>
  </div>
</template>

<script>
export default {
  name: "UpdateInfo",
  data () {
    return {
      userinfo:{},
      updateForm: {
        username: '',
        password: '',
        email: '',
        introduction: ''
      },
      responseResult: []
    }
  },
  created() {
    this.$axios.post('/user/logined').then(successResponse => {
      this.userinfo=successResponse.data
    })
  },
  methods: {
    update() {
      this.$axios
        .put('/user/'+this.userinfo.uid, {
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
    },
    deleteUser() {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete("/user/"+this.userinfo.uid)
        this.$message.success('删除成功!');
        this.$router.replace({path: "/login"})
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    }
  }
}
</script>

<style scoped>

</style>
