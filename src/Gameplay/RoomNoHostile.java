package Gameplay;
import Area.Tektik.TektikVar;
import Characters.Player;
import Gameplay.Generation.ResponseGen;


public interface RoomNoHostile {
	public void check(TektikVar v, String c, Player p);

	public String talkTo();

	public String north(Player p);

	public String east(Player p);

	public String south(Player p);

	public String west(Player p);

	/**
	 * Activates the "use" command. Checks for possible outputs with extra
	 * parameters.
	 * 
	 * @param command
	 *            Parameter interpretation. Required for appropriate output and
	 *            progress.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String use(Player p, String c, TektikVar v) ;
	public String take(Player p, String c, TektikVar v);

	public String examine(Player p, String c, TektikVar v) ;
	
}
