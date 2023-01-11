<template>
  <div>
    <div class="nav">
      <div v-for="(item,index) in navList" :key="index" :class="index === active?'active':''" @mousemove="active = index" @mouseout="active = null" @click="toPage(index)">{{ item.name }}
      </div>
    </div>
    <el-button type="info" v-on:click="back">返回</el-button>
    <br><br>
<!--    <el-avatar shape="square" :size="1000" :src="require('../assets/'+roomForm.graph)"></el-avatar>-->
    <div class="bkg" :style="bkg">
      <div v-for="item in equipForm">
        <div v-if="item.type===1">
          <vdr :w="100" :h="100" :x="item.x" :y="item.y" :resizable="false" :isConflictCheck="true" :parent="false" @dragging="onDragStartCallback(item.eid)" v-on:dragging="onDrag" @dragstop="onDragstop" >
            <el-avatar shape="square" :size="80" :src="require('../assets/lamp.jpeg')"></el-avatar>
            <span v-text="item.equipname"></span>
            <el-switch
              v-model="item.status"
              @change="changeStatus(item.eid,item.status)"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
            <br><br>
            <el-slider v-model="item.luminance" @change="changeLuminance(item.eid,item.luminance)"></el-slider>
          </vdr>
        </div>
        <div v-else-if="item.type===2">
          <vdr :w="100" :h="100" :x="item.x" :y="item.y" :resizable="false" :isConflictCheck="true" :parent="false" @dragging="onDragStartCallback(item.eid)" v-on:dragging="onDrag" @dragstop="onDragstop" >
            <el-avatar shape="square" :size="80" :src="require('../assets/switch.jpeg')"></el-avatar>
            <span v-text="item.equipname"></span>
            <el-switch
              v-model="item.status"
              @change="changeStatus(item.eid,item.status)"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </vdr>
        </div>
        <div v-else-if="item.type===3">
          <vdr :w="100" :h="100" :x="item.x" :y="item.y" :resizable="false" :isConflictCheck="true" :parent="false" @dragging="onDragStartCallback(item.eid)" v-on:dragging="onDrag" @dragstop="onDragstop" >
            <el-avatar shape="square" :size="80" :src="require('../assets/sensor.jpeg')"></el-avatar>
            <span v-text="item.equipname"></span>
            <br><br>
            温度：<span v-text="item.temperature"></span>
            <br><br>
            湿度：<span v-text="item.humidity"></span>
          </vdr>
        </div>
        <div v-else-if="item.type===4">
          <vdr :w="100" :h="100" :x="item.x" :y="item.y" :resizable="false" :isConflictCheck="true" :parent="false" @dragging="onDragStartCallback(item.eid)" v-on:dragging="onDrag" @dragstop="onDragstop" >
            <el-avatar shape="square" :size="80" :src="require('../assets/lock.jpeg')"></el-avatar>
            <span v-text="item.equipname"></span>
            <el-switch
              v-model="item.status"
              @change="changeLockStatus(item.eid,item.status)"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </vdr>
        </div>
        <div v-else>error type</div>
      </div>
<!--      <vdr :w="100" :h="100" :resizable="false" :isConflictCheck="true" :parent="true" v-on:dragging="onDrag" @dragstop="onDragstop" >-->
<!--        <p>X: {{ tempx }}  Y: {{ tempy }}</p>-->
<!--      </vdr>-->
<!--      <vdr-->
<!--        :w="100"-->
<!--        :h="100"-->
<!--        :resizable="false"-->
<!--        :parent="true"-->
<!--        :debug="false"-->
<!--        :isConflictCheck="true"-->
<!--        :snap="false"-->
<!--      >-->
<!--        <el-avatar shape="square" :size="100" :src="require('../assets/'+roomForm.graph)"></el-avatar>-->
<!--      </vdr>-->
    </div>
  </div>
</template>

<script>
import vdr from 'vue-draggable-resizable-gorkys'
import 'vue-draggable-resizable-gorkys/dist/VueDraggableResizable.css'
export default {
  name: "Room",
  data () {
    return {
      rid: 0,
      eid: 0,
      active: null,
      navList: [
        { name: '个人中心' },
        { name: '房间列表' },
        { name: '设备列表' },
        { name: '退出登录' },
      ],
      bkg: {
        backgroundImage: "",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        marginTop: "5px",
        marginLeft: "25%",
        width: "50%",
        height: "100%",
        position: "fixed",
      },
      roomForm: {},
      equipForm: [],
      tempx: 0,
      tempy: 0,
      isDrag: false
    }
  },
  created() {
    this.rid=this.$route.params.rid;
    this.active = this.$route.query.active
    this.$axios.post('/room/getroom',{rid: this.rid}).then(successResponse => {
      this.roomForm=successResponse.data
      this.bkg.backgroundImage = "url(" + require("../assets/"+this.roomForm.graph) + ")"
    })
    this.$axios.post('/equipment/room',{rid: this.rid}).then(successResponse => {
      this.equipForm=successResponse.data
      this.$message.success(this.equipForm)
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
          break
      }
    },
    back(){
      this.$router.push({path: '/roomlist'})
    },
    onDragStartCallback(eid){
      if (!this.isDrag){
        this.isDrag=true
        this.eid = eid
      }
    },
    onDrag(x,y){
      this.tempx = x
      this.tempy = y
    },
    onDragstop: function (x,y){
      if (this.isDrag){
        this.isDrag=false
        this.$message.info("isDrag:("+x+","+y+"),eid="+this.eid)
        this.$axios.
        put("/equipment/xy/"+this.eid,{
          x: x,
          y: y
        })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.success("("+x+","+y+")")
            }
          })
      }
      else {
        this.$message.info("notDrag:("+x+","+y+"),eid="+this.eid)
      }
    },
    changeStatus(eid,status){
      this.$axios.
      put("/equipment/status/"+eid,{
        status: status
      })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$message.success("开关状态为"+status)
          }
        })
    },
    changeLuminance(eid,luminance){
      this.$axios.
      put("/equipment/luminance/"+eid,{
        luminance: luminance
      })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$message.success("亮度为"+luminance)
          }
        })
    },
    changeLockStatus(eid,status){
      this.$axios.
      put("/equipment/status/"+eid,{
        status: status
      })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            if (status){
              this.$message.success("门已关锁")
            }
            else{
              this.$message.warning("门已开锁")
            }
          }
        })
    },
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
  font-size: 20px;
  cursor: pointer;
}

.active {
  background: #486341;
  color: #ffffff;
// padding: 0 5px;
//min-width: 120px;
}
</style>
