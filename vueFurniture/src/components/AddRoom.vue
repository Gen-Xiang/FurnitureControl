<template>
  <div>
    <el-button type="info" v-on:click="back">返回</el-button>
    添加房间
    <br><br>
    房间名:<input type="text" v-model="roomForm.roomname" placeholder="请输入房间名"/>
    <br><br>
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
<!--      <img v-if="$hostURL+imageUrl" :src="$hostURL+imageUrl" class="avatar">-->
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>

    <el-button type="primary" @click="submitUpload">添加房间</el-button>
<!--    <button v-on:click="add">添加房间</button>-->
  </div>
</template>

<script>
export default {
  name: "AddRoom",
  data () {
    return {
      // uid: 0,
      roomForm:{
        uid: 0,
        roomname: '',
        graph: ''
      }
    }
  },
  created() {
    // this.uid=this.$route.params.uid;
    this.roomForm.uid=this.$route.params.uid;
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
        url:'/room/addroom/'+this.roomForm.uid+'/'+this.roomForm.roomname,
        data:params,
        headers:{
          'content-type':'multipart/form-data'
        }
      }).then(res=>{
        if (res.data!=null){
          this.$message.success("创建房间成功，rid为"+res.data.rid)
        }
        else{
          this.$message.error("创建失败！")
        }
      })
    },
    handlePreview(){
      this.$message.info("这是Preview")
    },
    handleRemove(){
      this.$message.warning("删除图片")
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

</style>
