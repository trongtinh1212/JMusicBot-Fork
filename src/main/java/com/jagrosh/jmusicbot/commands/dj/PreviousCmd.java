package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.audio.QueuedTrack;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class PreviousCmd extends DJCommand {
    public PreviousCmd(Bot bot) {
        super(bot);
        this.name = "previous";
        this.help = "plays the previous track";
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();

        if(handler.getPreviousTrack() != null) {
            if(handler.getNowPlaying(bot.getJDA()) != null) {
                try {
                    handler.getPlayer().getPlayingTrack().stop();
                    handler.getQueue().addAt(0, new QueuedTrack(handler.getPreviousTrack().makeClone(), handler.getPreviousTrack().getUserData(Long.class) == null ? 0L : handler.getPreviousTrack().getUserData(Long.class)));
                } finally {
                    event.replySuccess("Previous loaded!");
                }
            } else {
                try {
                    handler.getPlayer().playTrack(handler.getPreviousTrack().makeClone());
                } finally {
                    event.replySuccess("Previous loaded!");
                }
            }
        } else {
            event.replyError("Previous is null!");
        }
    }
}
