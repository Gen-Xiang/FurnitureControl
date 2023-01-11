import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld';
import Home from '../components/Home';
import Login from '../components/Login';
import Register from "../components/Register";
import UpdateInfo from "../components/UpdateInfo";
import RoomList from "../components/RoomList";
import Room from "../components/Room";
import AddRoom from "../components/AddRoom";
import EquipmentList from "../components/EquipmentList";
import AddEquipment from "../components/AddEquipment";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/updateinfo',
      name: 'UpdateInfo',
      component: UpdateInfo
    },
    {
      path: "/roomlist",
      name: "RoomList",
      component: RoomList
    },
    {
      path: "/room/:rid",
      name: "Room",
      component: Room
    },
    {
      path: "/addroom",
      name: "AddRoom",
      component: AddRoom
    },
    {
      path: "/equipmentlist",
      name: "EquipmentList",
      component: EquipmentList
    },
    {
      path: "/addequipment",
      name: "AddEquipment",
      component: AddEquipment
    }

  ]
})
