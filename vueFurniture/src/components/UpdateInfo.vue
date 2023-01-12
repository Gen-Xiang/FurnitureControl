<template>
  <body id="poster">
  <el-button type="info" style="margin-top: 50px; text-align: left" v-on:click="back">返回</el-button>
  <el-form class="update-container" label-position="left">
    <h2 class="update_title">修改个人信息</h2>
    <el-form-item label="用户名">
      <el-input type="text" label-width="auto" v-model="updateForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" label-width="auto" v-model="updateForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item label="电子邮箱">
      <el-input type="text" label-width="auto" v-model="updateForm.email" auto-complete="off" placeholder="请输入电子邮箱"></el-input>
    </el-form-item>
    <el-form-item label="个人介绍">
      <el-input type="text" label-width="auto" v-model="updateForm.introduction" auto-complete="off" placeholder="请输入个人介绍"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" v-on:click="update">提交修改</el-button>
      <br><br>
      <el-button type="danger" v-on:click="deleteUser">注销账户</el-button>
    </el-form-item>
  </el-form>
  </body>
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
          else if (successResponse.data.code === 401){
            this.$message.error("用户名已被其他人占用");
          }
          else if (successResponse.data.code === 402){
            this.$message.error("电子邮箱已被其他人占用");
          }
          else if (successResponse.data.code === 403){
            this.$message.error("密码长度少于6位");
          }
        })
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
.update-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 30px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.update_title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
