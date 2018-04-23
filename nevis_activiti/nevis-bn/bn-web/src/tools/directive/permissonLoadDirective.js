import Vue from 'vue'
import permission from './loadPermission'

/**
 *自定义指令控制资源显示或隐藏
 * @author ljl
 */
Vue.directive('auth',{
  inserted : (el,binding,vnode)=>{
    if(!permission.valid(binding.value)) {
      el.parentNode.removeChild(el);
    }
  }
})



