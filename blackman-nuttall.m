N=128;
k=0:N-1;
 
dr = 120;
w = 0.3635819 - 0.4891775*cos(2*pi*k/(N-1)) + 0.1365995*cos(4*pi*k/(N-1)) -0.0106411*cos(6*pi*k/(N-1));
 
B = N*sum(w.^2)/sum(w)^2    % noise bandwidth (bins)
 
H = abs(fft([w zeros(1,7*N)]));
H = fftshift(H);
H = H/max(H);
H = 20*log10(H);
H = max(0,dr+H);
 
figure
area(k,w,'FaceColor', [0 .4 .6])
xlim([0 N-1])
set(gca,'XTick', [0 : 1/8 : 1]*(N-1))
set(gca,'XTickLabel','0| | | | | | | |N-1')
grid on
ylabel('amplitude')
xlabel('samples')
title('Window function (Blackman-Nuttall)')
 
figure
stem(([1:(8*N)]-1-4*N)/8,H,'-');
set(findobj('Type','line'),'Marker','none','Color',[.871 .49 0])
xlim([-4*N 4*N]/8)
ylim([0 dr])
set(gca,'YTickLabel','-120|-100|-80|-60|-40|-20|0')
grid on
ylabel('decibels')
xlabel('DFT bins')
title('Frequency response (Blackman-Nuttall)')