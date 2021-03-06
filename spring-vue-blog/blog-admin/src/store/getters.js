import global from "@/store/modules/global";

const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  typeList: state => state.global.typeList
}
export default getters
