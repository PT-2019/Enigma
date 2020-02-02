package editor.menus;

import api.utils.Utility;
import editor.menus.content.SetContent;
import editor.menus.item.AddItems;
import editor.menus.others.DefineAsHero;
import editor.menus.others.Delete;
import editor.menus.others.HidePassageRoom;
import editor.menus.enimas.ManageEnigmas;
import editor.menus.others.SetAccess;
import editor.menus.others.SetName;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Les options runnables
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public final class OptionRunnableFactory {

	/**
	 * Retourne tous les options runnable disponibles
	 * @return tous les options runnable disponibles
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Class<AvailableOptionRunnable>> getAll(){
		ArrayList<Class<AvailableOptionRunnable>> all = new ArrayList<>();

		for (Method m: OptionRunnableFactory.class.getMethods()) {
			//si c'est une bonne
			if(m.getReturnType().getName().equals(Class.class.getName())
					&& m.getDeclaringClass().equals(OptionRunnableFactory.class)){
				Class aClass = Utility.invokeMethod(m, Class.class);
				if(aClass != null) all.add(aClass);
			}
		}

		return all;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> setAccess(){
		return SetAccess.class;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> defineAsHero(){
		return DefineAsHero.class;
	}

	@SuppressWarnings("unused")
	@Deprecated
	public static Class<? extends AvailableOptionRunnable> defineAsNpc(){
		return null;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> delete(){
		return Delete.class;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> hidePassageRoom(){
		return HidePassageRoom.class;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> manageEnigmas(){
		return ManageEnigmas.class;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> addItems(){
		return AddItems.class;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> setContent(){
		return SetContent.class;
	}

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> setName(){ return SetName.class; }

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> setLocked(){ return null; }

	@SuppressWarnings("unused")
	public static Class<? extends AvailableOptionRunnable> setActivated(){ return null; }
}
