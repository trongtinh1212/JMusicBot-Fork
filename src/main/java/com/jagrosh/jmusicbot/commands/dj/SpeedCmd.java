package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.audio.QueuedTrack;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class SpeedCmd extends DJCommand {
    public SpeedCmd(Bot bot) {
        super(bot);
        this.name = "speed";
        this.help = "changes track speed";
        this.arguments = "1|2.5";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

        if(Double.isNaN(Double.parseDouble(event.getArgs()))) {
            event.replyError("Only numbers supported!");
        } else if(handler.getPlayer().getPlayingTrack() == null) {
            event.replyError("No playing track!");
        } else {
            long lenght = handler.getPlayer().getPlayingTrack().getPosition();
            handler.setSpeed(event.getGuild(), Double.parseDouble(event.getArgs()));
            handler.getQueue().add(new QueuedTrack(handler.getPlayer().getPlayingTrack().makeClone(), handler.getPlayer().getPlayingTrack().getUserData(Long.class) == null ? 0L :handler.getPlayer().getPlayingTrack().getUserData(Long.class)));
            handler.getPlayer().stopTrack();
            handler.getPlayer().getPlayingTrack().setPosition(lenght);
            event.replySuccess("Speed now: " + "`" + event.getArgs() + "`");
        }

    }
}
