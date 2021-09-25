package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class ConnectCmd extends DJCommand {
    public ConnectCmd(Bot bot) {
        super(bot);
        this.name = "connect";
        this.help = "bot will leave and connect specified channel";
        this.arguments = "<nothing or channel id>";
    }

    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        VoiceChannel current = event.getGuild().getSelfMember().getVoiceState().getChannel();
        GuildVoiceState userState = event.getMember().getVoiceState();
        VoiceChannel arg = event.getGuild().getVoiceChannelById(event.getArgs());

    }
}
