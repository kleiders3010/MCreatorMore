@Mod.EventBusSubscriber public class ${name}Procedure {
	@SubscribeEvent public static void onMCreatorLinkPinChanged(LinkDigitalPinChangedEvent event){
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("pin",event.getPin());
		dependencies.put("value",(int)event.getValue());
		dependencies.put("risingEdge",event.isRisingEdge());
		dependencies.put("event",event);
		execute(dependencies);
	}