package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class RemoveRangeCmd extends DJCommand {
    public RemoveRangeCmd(Bot bot) {
        super(bot);
        this.name = "removerange";
        this.help = "Deletes tracks in queue that are specified in the range";
        this.arguments = "x x|1 3";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.beListening = false;
        this.bePlaying = true;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

    }
}
