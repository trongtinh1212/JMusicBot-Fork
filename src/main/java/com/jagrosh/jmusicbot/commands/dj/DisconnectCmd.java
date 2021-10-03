package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class DisconnectCmd extends DJCommand {
    public DisconnectCmd(Bot bot) {
        super(bot);
        this.name = "disconnect";
        this.help = "bot will leave the channel";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void doCommand(CommandEvent event) {
        VoiceChannel current = event.getGuild().getSelfMember().getVoiceState().getChannel();
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();

        if(current != null) {
            try {
                handler.stopAndClear();
                event.getGuild().getAudioManager().closeAudioConnection();
            } finally {
                event.replySuccess("Bye!");
            }
        } else {
            event.replyError("Im not in voice channel!");
        }
    }
}
