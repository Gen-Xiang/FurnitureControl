<template>
  <body id="poster">
    <el-button type="info" style="margin-top: 50px; text-align: left" v-on:click="back">返回</el-button>
    <el-form class="addroom-container" label-position="left">
      <h1 class="addroom_title">添加房间</h1>
      <el-form-item label="房间名">
        <el-input type="text" label-width="auto" v-model="roomForm.roomname" auto-complete="off" placeholder="请输入房间名"></el-input>
      </el-form-item>
      <el-form-item label="上传图片">
        <el-upload
          list-type="picture-card"
          action="#"
          ref="upload"
          :http-request="picUpload"
          :show-file-list="true"
          :auto-upload="false"
          :on-remove="handleRemove"
          :on-success="handleSuccess"
          :before-upload="beforeUpload"
          :multiple="false"
          :limit="1"
          :on-exceed="handleExceed"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click="submitUpload">添加房间</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: "AddRoom",
  data () {
    return {
      userinfo:{},
      roomForm:{
        roomname: '',
        graph: ''
      }
    }
  },
  created() {
    this.$axios.post('/user/logined').then(successResponse => {
      this.userinfo=successResponse.data
    })
  },
  methods:{
    handleSuccess(res,file){
      this.roomForm.graph = URL.createObjectURL(file.raw);
    },
    beforeUpload(file){
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    picUpload(f){
      let params = new FormData()
      params.append("file",f.file);
      this.$axios({
        method:'post',
        url:'/room/addroom/'+this.userinfo.uid+'/'+this.roomForm.roomname,
        data:params,
        headers:{
          'content-type':'multipart/form-data'
        }
      }).then(res=>{
        if (res.data!=null){
          this.$message.success("创建房间成功，rid为"+res.data.rid)
          this.$router.replace({path: "/roomlist"})
        }
        else{
          this.$message.error("创建失败！")
          this.$router.replace({path: "/roomlist"})
        }
      })
    },
    handlePreview(){
      console.log("这是Preview")
    },
    handleRemove(){
      console.log("删除图片")
    },
    handleExceed(){
      this.$message.error("只能上传一张照片")
    },
    submitUpload(){
      this.$refs.upload.submit();
    },
    back() {
      this.$router.replace({path: '/roomlist'})
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
.addroom-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 30px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.addroom_title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
