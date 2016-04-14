# TryEventBus

EventBus是一款针对Android优化的发布/订阅事件总线。
主要功能是替代Intent,Handler,BroadCast在Fragment，Activity，Service，
线程之间传递消息.优点是开销小，代码更优雅。以及将发送者和接收者解耦。

EventBus的简单实现
1）自定义一个类，可以是空类，比如：
	public class AnyEventType {  
	     public AnyEventType(){}  
	 }  
	 
（2）在要接收消息的页面注册：
	eventBus.register(this);  
	
（3）发送消息 
	eventBus.post(new AnyEventType event);  
	
（4）接受消息的页面实现(共有四个函数，各功能不同，这是其中之一，可以选择性的实现，这里先实现一个)：  
	public void onEventMainThread (AnyEventType event) {}  
	
（5）解除注册
	eventBus.unregister(this);  
	
1、onEvent
2、onEventMainThread
3、onEventBackgroundThread
4、onEventAsync

EventBus是怎么接收消息的，是根据参数中类的实例的类型的判定的，所以当如果我们在接收时
，同一个类的实例参数有两个函数来接收会怎样？答案是，这两个函数都会执行。

