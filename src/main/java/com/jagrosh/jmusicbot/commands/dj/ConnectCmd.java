package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class ConnectCmd extends DJCommand {
    public ConnectCmd(Bot bot) {
        super(bot);
        this.name = "connect";
        this.help = "bot will leave and connect specified channel";
        this.arguments = "<nothing or channel id>";
    }

    public void doCommand(CommandEvent event)  {
        Logger log = LoggerFactory.getLogger("MusicBot");
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        VoiceChannel current = event.getGuild().getSelfMember().getVoiceState().getChannel();
        GuildVoiceState userState = event.getMember().getVoiceState();

        if(event.getArgs().length() == 0 && !userState.inVoiceChannel() && event.getArgs().equals(Long.class)) {
            event.replyError("Channel not selected!");
        }  else {
            event.replyError("Invalid channel!");
        }
        if(current != null) if(event.getArgs().length() != 0 && event.getArgs() == current.getId() && event.getArgs().equals(Long.class)) {
            event.replyError("Im already in this voice!");
        }
        if(event.getArgs().length() != 0) {
            if(event.getArgs().equals(Long.class) && event.getGuild().getVoiceChannelById(event.getArgs()) == null) {
                event.replyError("Unable to find channel!");
            }
        }

        if(event.getArgs().length() == 0 && userState.inVoiceChannel()) {
            if(handler.getNowPlaying(event.getJDA()) != null) {
                handler.getPlayer().setPaused(true);
                try {
                    event.getGuild().getAudioManager().openAudioConnection(userState.getChannel());
                }finally {
                    handler.getPlayer().setPaused(false);
                    event.replySuccess("Now in voice: <#" + userState.getId() + ">");
                }
            } else {
                event.getGuild().getAudioManager().openAudioConnection(userState.getChannel());
            }
        } else if(event.getArgs().equals(Long.class) && event.getGuild().getVoiceChannelById(event.getArgs()) != null) {
            try {
                event.getGuild().getAudioManager().openAudioConnection(event.getGuild().getVoiceChannelById(event.getArgs()));

            } finally {
                handler.getPlayer().setPaused(false);
                event.replySuccess("Now in voice: <#" + event.getArgs() + ">");
            }
        }
    }
}


