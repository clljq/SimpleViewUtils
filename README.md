##Android-SimpleViewUtils##
这是一个简单的基于注解的UI绑定和事件绑定的工具类，无需findViewById和setOnClickListener，将UI的初始化和事件绑定交给框架来完成，提高开发效率
  
这个工具类只是用来展示自定义注解和反射的使用，实际项目中还是建议使用butterknife  

这个工具类仅支持在Activity中通过注解的方式初始化View和事件绑定，支持多个View绑定同一个点击事件

##用法
###初始化View
    public class MainActivity extends AppCompatActivity {

	    @ViewInject(R.id.tv)
	    private TextView textView;
	
	    @ViewInject(R.id.btn)
	    private Button button;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        //开始注入
	        ViewUtils.inject(this);
    }
###绑定事件
    @ClickInject({R.id.btn1,R.id.btn2,R.id.btn3})
    private void click(View view) {
        Toast.makeText(MainActivity.this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        break;
        }
    }