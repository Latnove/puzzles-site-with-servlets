function escapeHtml(input) {
    if (!isNaN(input)) {
        return input
    }
    if (!input) {
        return '';
    }
    return input.replace(/[&<>"']/g, function(match) {
        switch (match) {
            case '&':
                return '&amp;';
            case '<':
                return '&lt;';
            case '>':
                return '&gt;';
            case '"':
                return '&quot;';
            case "'":
                return '&#39;';
        }
    });
}