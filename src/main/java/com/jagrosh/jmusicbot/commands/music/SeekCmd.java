package com.jagrosh.jmusicbot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.audio.PlayerManager;
import com.jagrosh.jmusicbot.commands.MusicCommand;
import com.jagrosh.jmusicbot.settings.Settings;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;

import java.util.regex.Pattern;

public class SeekCmd extends MusicCommand {
    private AudioPlayer audioPlayer;
    private PlayerManager manager;
    CommandEvent guild;

    public SeekCmd(Bot bot) {
        super(bot);
        this.audioPlayer = audioPlayer;
        this.manager = manager;
        this.name = "seek";
        this.arguments = "<HH:MM:SS>|<MM:SS>|<SS>";
        this.help = "seek song to the time in arguments";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.beListening = true;
        this.bePlaying = true;
    }


    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

        if (handler.getPlayer().getPlayingTrack().isSeekable()) {
            AudioTrack currentTrack = handler.getPlayer().getPlayingTrack();
            Settings settings = event.getClient().getSettingsFor(event.getGuild());

            if (!event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
                if (!event.getMember().getRoles().contains(settings.getRole(event.getGuild()))) {
                    if (currentTrack.getUserData(Long.class) != event.getAuthor().getIdLong()) {
                        event.replyError("You cannot seek **" + currentTrack.getInfo().title + "** because you didn't add it!");
                        return;
                    }
                }
            }
        }
        String args = event.getArgs();
        long track_duration = handler.getPlayer().getPlayingTrack().getDuration();
        int seek_milliseconds = 0;
        int seconds;
        int minutes = 0;
        int hours = 0;

        if (Pattern.matches("^([0-9]\\d):([0-5]\\d):([0-5]\\d)$", args)) {
            hours = Integer.parseInt(args.substring(0, 2));
            minutes = Integer.parseInt(args.substring(3, 5));
            seconds = Integer.parseInt(args.substring(6));
        } else if (Pattern.matches("^([0-5]\\d):([0-5]\\d)$", args)) {
            minutes = Integer.parseInt(args.substring(0, 2));
            seconds = Integer.parseInt(args.substring(3, 5));
        } else if (Pattern.matches("^([0-5]\\d)$", args)) {
            seconds = Integer.parseInt(args.substring(0, 2));
        } else {
            event.replyError("Invalid seek!");
            return;
        }
        seek_milliseconds += hours * 3600000 + minutes * 60000 + seconds * 1000;
        if (seek_milliseconds <= track_duration) {
            handler.getPlayer().getPlayingTrack().setPosition(seek_milliseconds);
            event.replySuccess("Successfully seeked!");
        } else {
            event.replyError("Current track is not that long!");
        }
    }
}
