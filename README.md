# TagLayout
taglayout
<br/>
<h4>属性</h4>
<ul>
<li>horizontal_space 水平间距，tag与tag的距离</li>
<li>vertical_space 垂直距离，上下tag的距离</li>
</ul>

<br/>
<h4>方法</h4>
<ul>
<li>setCheckable 是否具有选中功能</li>
<li>vertical_space 垂直距离，上下tag的距离</li>
<li>setPadding(int lr, int tp)  lr 左右padding,tp 上下padding （都是以dp为单位）</li>
<li>setOnItemClick(OnItemClick mOnItemClick)   tag点击事件</li>
<li>setTags(List<? extends Object> list, BindProperty onBindProperty)  list中的对象必须覆写toString 方法</li>
 public interface BindProperty {
        void OnBindProperty(TextView view);
    }
    接口你可以给tag赋予想要的样式 ，字体颜色等</li>
    </ul>
    
<br/>
<h4>screenshot</h4>
<br/>
<img src="screenshot.png"/>
