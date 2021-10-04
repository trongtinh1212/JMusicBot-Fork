package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.audio.QueuedTrack;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class DepthCmd extends DJCommand {
    public DepthCmd(Bot bot) {
        super(bot);
        this.name = "depth";
        this.help = "changes track depth";
        this.arguments = "1|0.75";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

        if(event.getArgs().length() != 0) {
            if (Float.isNaN(Float.parseFloat(event.getArgs()))) {
                event.replyError("Only float supported!");
            } else if (handler.getPlayer().getPlayingTrack() == null) {
                event.replyError("No playing track!");
            } if(Float.parseFloat(event.getArgs()) > 1) {
                event.replyError("Cannot set depth bigger than 1");
            } if(Float.parseFloat(event.getArgs()) <= 0) {
                event.replyError("Cannot set depth smaller than 0");
            } else {
                event.replySuccess("Depth now: " + "`" + event.getArgs() + "`");
            }
        } else {
            event.replyError("Invalid args!");
        }

    }
}
